package at.ahammer.boardgame.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * This class represents a CDI-context for a game. The context has to be
 * started at the begin of a game and will (automatically) be
 * destroyed at the end of a game. All components with the
 * annotation {@link at.ahammer.boardgame.cdi.GameScoped} will be unique in this context.
 * <p/>
 * To start the context use
 * {@link #run(javax.enterprise.inject.spi.BeanManager, RunInGameContext)}.
 * <p/>
 * To reuse an existing context use {@link #run(java.util.UUID, javax.enterprise.inject.spi.BeanManager, RunInGameContext)}
 *
 * @author Andreas
 */
@Singleton
public class GameContext implements Context {

    /**
     * The {@link java.util.Stack} which holds the {@link at.ahammer.boardgame.cdi.GameContext}.
     */
    private static final ThreadLocal<Stack<GameContextInstance>> gameContextStack = new ThreadLocal<>();

    /**
     * A cache for all created {@link GameContextInstance}s. This instances can be reused.
     */
    private static final Map<UUID, GameContextInstance> gameContextCache = Collections.synchronizedMap(new HashMap<>());

    private static final Logger log = LoggerFactory.getLogger(GameContext.class);

    @Inject
    private GameContextCacheDeleteStrategy gameContextCacheDeleteStrategy;

    @Inject
    private GameContextDeleteTimer gameContextDeleteTimer;

    /**
     * The assigned cod        e will run within a new {@link at.ahammer.boardgame.cdi.GameContext}. The
     * context will be started automatically and will be destroyed afterwards.
     *
     * @param runnable - the {@link at.ahammer.boardgame.cdi.RunInGameContext} which code must run within a
     *                 {@link at.ahammer.boardgame.cdi.GameContext}
     * @return the result of the {@link at.ahammer.boardgame.cdi.RunInGameContext}
     */
    public static <T> T run(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        return run(null, beanManager, runnable);
    }

    /**
     * Start a new {@link GameContext} or reuse an existing one via the {@code gameContextId}. The code within the {@code runnable} will be executed within the {@link GameContext}.
     * <p/>
     * This method will throw {@link Throwable}, because for JUnit-Tests.
     *
     * @param gameContextId the id (a {@link java.util.UUID} to reuse an existing {@link GameContextInstance}
     * @param beanManager   the current {@link javax.enterprise.inject.spi.BeanManager}
     * @param runnable      the code run within the {@link GameContext}
     * @param <T>           the return-type
     * @return the id of the used {@link GameContextInstance}
     * @throws Throwable
     */
    public static <T> T run(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        GameContextInstance gameContextInstance = getGameContextInstance(gameContextId, beanManager);
        return gameContextInstance.getFromDynamicContext(GameContext.class).runInternal(gameContextInstance, runnable);
    }

    /**
     * Add a new instance of {@link NewInstanceInGameContext} created within the current {@link GameContextInstance}.
     *
     * @param newInstanceInGameContext the new object
     */
    public static void addNewInstanceInGameContext(NewInstanceInGameContext newInstanceInGameContext) {
        current().addNewInstanceToGameContext(newInstanceInGameContext);
    }

    /**
     * Get all objects created in the current {@link GameContextInstance} which are subclasses of {@link NewInstanceInGameContext}.
     *
     * @return all objects created in the current {@link GameContextInstance}
     */
    public static Set<NewInstanceInGameContext> getNewInstancesInGameContext() {
        return current().getNewInstancesInGameContext();
    }

    /**
     * Get the {@link java.util.Stack} holding all {@link GameContextInstance}s in the current {@link Thread}.
     *
     * @return the {@link java.util.Stack} holding all {@link GameContextInstance}s in the current {@link Thread}
     */
    private static Stack<GameContextInstance> getGameContextStack() {
        Stack<GameContextInstance> stack = gameContextStack.get();
        if (stack == null) {
            stack = new Stack<>();
            gameContextStack.set(stack);
        }
        return stack;
    }

    /**
     * Get a new {@link GameContextInstance} or create a new one if the assigned {@code gameContextId} is null or the {@link GameContextInstance} for this is is not available.
     *
     * @param gameContextId - the id of the {@link GameContextInstance} to reuse. If it is null, a new {@link GameContextInstance} will create.
     * @return the {@link GameContextInstance}
     */
    private static GameContextInstance getGameContextInstance(UUID gameContextId, BeanManager beanManager) {
        GameContextInstance gameContextInstance = null;
        if (gameContextId != null) {
            gameContextInstance = gameContextCache.get(gameContextId);
        }
        if (gameContextInstance == null) {
            if (gameContextId != null) {
                log.info("no {}  with id {} available", GameContextInstance.class.getSimpleName(), gameContextId);
            }
            gameContextId = UUID.randomUUID();
            log.info("create new {} with id {}", GameContextInstance.class.getSimpleName(), gameContextId);
            gameContextInstance = new GameContextInstance(gameContextId, beanManager);
            gameContextCache.put(gameContextId, gameContextInstance);
        } else {
            log.info("reuse {} with id {}", GameContextInstance.class.getSimpleName(), gameContextId);
        }
        return gameContextInstance;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return get(contextual, null);
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        if (!(contextual instanceof Bean)) {
            throw new RuntimeException("contextual must be an instance of " + Bean.class + " but is " + contextual);
        }
        Bean<T> bean = (Bean<T>) contextual;
        if (getGameContextInstance().isAvailableInCreationalContext(bean, creationalContext)) {
            // bean is already available in context -> return
            return getGameContextInstance().getFromCreationalContext(bean, creationalContext);
        }
        if (creationalContext == null) {
            // creationalContext is null -> return null
            return null;
        } else {
            // create and return bean
            return getGameContextInstance().addToCreationalContext(bean, creationalContext);
        }
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return GameScoped.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Get the current {@link GameContextInstance}.
     *
     * @return the current {@link GameContextInstance}
     */
    private GameContextInstance getGameContextInstance() {
        if (getGameContextStack().isEmpty()) {
            throw new GameContextException(new ContextNotActiveException("This context may only be used inside an active " + this.getClass().getName()));
        }
        return getGameContextStack().peek();
    }

    private <T> T runInternal(GameContextInstance gameContextInstance, RunInGameContext<T> runnable) throws Throwable {
        // remove old GameContexts from cache
        if (gameContextDeleteTimer.shouldCheck()) {
            log.info("check if a {} should be removed", GameContextInstance.class.getSimpleName());
            gameContextCacheDeleteStrategy.getContextsToDelete(gameContextCache, gameContextInstance.getId()).stream().forEach((contextId) -> {
                log.info("remove {} with id {}", GameContextInstance.class.getSimpleName(), contextId);
                gameContextCache.remove(contextId);
            });
        }
        // add a new GameContext to the stack
        getGameContextStack().push(gameContextInstance);
        try {
            // run the code within the new GameContext
            return runnable.call(gameContextInstance.getId());
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else if (e instanceof RuntimeException) {
                throw new RuntimeException(e);
            } else {
                throw e;
            }
        } finally {
            // remove the GameContext from the stack
            getGameContextStack().pop();
        }
    }

//    public void destroy(@Observes KillEvent killEvent) {
//        if (customScopeContextHolder.getBeans().containsKey(killEvent.getBeanType())) {
//            customScopeContextHolder.destroyBean(customScopeContextHolder.getFromDynamicContext(killEvent.getBeanType()));
//        }
//    }

    /**
     * Get the current {@link GameContextInstance}.
     *
     * @return the current {@link GameContextInstance}
     */
    public static GameContextInstance current() {
        return getGameContextStack().peek();
    }
}
