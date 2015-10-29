package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextException;
import org.jabogaf.api.gamecontext.GameContextInstance;
import org.jabogaf.api.gamecontext.RunInGameContext;
import org.jabogaf.util.log.LogWrapper;
import org.jabogaf.util.log.SLF4J;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

/**
 * Cache for some elements of the {@link GameContext}. All elements in this class are {@code static}.
 *
 * @author Andreas
 */
@Singleton
public class GameContextCache {

    /**
     * The {@link Stack} which holds the {@link GameContextInstance}s as {@link ThreadLocal}.
     */
    private static final ThreadLocal<Stack<GameContextInstance>> gameContextStack = new ThreadLocal<>();

    /**
     * A cache for all created {@link GameContextInstance}s. This instances can be reused.
     */
    private static final Map<UUID, GameContextInstance> gameContextCache = Collections.synchronizedMap(new HashMap<>());

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private GameContextCacheDeleteStrategy gameContextCacheDeleteStrategy;

    @Inject
    private GameContextDeleteTimer gameContextDeleteTimer;

    /**
     * Get the {@link Stack} holding all {@link GameContextInstance}s in the current {@link Thread}.
     *
     * @return the {@link Stack} holding all {@link GameContextInstance}s in the current {@link Thread}
     */
    private Stack<GameContextInstance> getGameContextStack() {
        Stack<GameContextInstance> stack = gameContextStack.get();
        if (stack == null) {
            stack = new Stack<>();
            gameContextStack.set(stack);
        }
        return stack;
    }

    private void checkIfOtherGameContextInstancesShouldBeRemoved(GameContextInstance currentGameContextInstance) {
        if (gameContextDeleteTimer != null && gameContextCacheDeleteStrategy != null) {
            // remove old GameContexts from cache
            if (gameContextDeleteTimer.shouldCheck()) {
                log.debug("check if a {} should be removed", GameContextInstance.class::getSimpleName);
                gameContextCacheDeleteStrategy.getContextsToDelete(gameContextCache, currentGameContextInstance.getId()).stream().forEach((contextId) -> {
                    log.debug("remove {} with id {}", Arrays.asList(GameContextInstance.class::getSimpleName, () -> contextId));
                    gameContextCache.remove(contextId);
                });
            }
        }
    }

    public GameContextInstance getCurrentGameContextInstance() {
        if (getGameContextStack().isEmpty()) {
            throw new GameContextException(new ContextNotActiveException("This context may only be used inside an active " + GameContext.class.getName()));
        }
        return getGameContextStack().peek();
    }

    public <T> T runInGameContext(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable) {
        GameContextInstance gameContextInstance = getGameContextInstance(gameContextId, beanManager);
        checkIfOtherGameContextInstancesShouldBeRemoved(gameContextInstance);
        // add a new GameContext to the stack
        getGameContextStack().push(gameContextInstance);
        try {
            // run the code within the new GameContext
            return runnable.call(gameContextInstance.getId());
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            // remove the GameContext from the stack
            getGameContextStack().pop();
        }
    }

    /**
     * Get a new {@link GameContextInstance} or create a new one if the assigned {@code gameContextId} is null or the
     * {@link GameContextInstance} for this is is not available.
     *
     * @param gameContextId - the id of the {@link GameContextInstance} to reuse. If it is null, a new {@link
     *                      GameContextInstance} will create.
     * @return the {@link GameContextInstance}
     */
    private GameContextInstance getGameContextInstance(final UUID gameContextId, BeanManager beanManager) {
        GameContextInstance gameContextInstance = null;
        if (gameContextId != null) {
            gameContextInstance = gameContextCache.get(gameContextId);
            checkIfOtherGameContextInstancesShouldBeRemoved(gameContextInstance);
        }
        if (gameContextInstance == null) {
            if (gameContextId != null) {
                log.debug("no {} with id {} available", Arrays.asList(GameContextInstance.class::getSimpleName, () -> gameContextId));
            }
            final UUID newGameContextId = UUID.randomUUID();
            log.debug("create new {} with id {}", Arrays.asList(GameContextInstance.class::getSimpleName, () -> newGameContextId));
            gameContextInstance = new GameContextInstanceBasic(newGameContextId, beanManager);
            gameContextCache.put(newGameContextId, gameContextInstance);
        } else {
            log.debug("reuse {} with id {}", Arrays.asList(GameContextInstance.class::getSimpleName, () -> gameContextId));
        }
        return gameContextInstance;
    }

    @SuppressWarnings("unused")
    @Produces
    @Dependent
    private GameContextInstance produceCurrentGameContextInstance() {
        return getCurrentGameContextInstance();
    }
}
