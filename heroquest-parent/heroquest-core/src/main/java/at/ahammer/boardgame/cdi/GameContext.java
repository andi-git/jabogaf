package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents a CDI-context for a game. The context has to be
 * started at the begin of a game and will (automatically) be
 * destroyed at the end of a game. All components with the
 * annotation {@link GameScoped} will be unique in this context.<br>
 * <br>
 * To start the context use
 * {@link #run(javax.enterprise.inject.spi.BeanManager, RunInGameContext)}.
 *
 * @author Andreas
 */
@Singleton
public class GameContext implements Context {

    /**
     * The {@link java.util.Stack} which holds the {@link GameContext}.
     */
    private static final ThreadLocal<Stack<GameContextInstance>> gameContextStack = new ThreadLocal<>();

    private static final Map<UUID, GameContextInstance> gameContextCache = new ConcurrentHashMap<>();

    /**
     * The assigned code will run within a new {@link GameContext}. The
     * context will be started automatically and will be destroyed afterwards.
     *
     * @param runnable - the {@link at.ahammer.boardgame.cdi.RunInGameContext} which code must run within a
     *                 {@link GameContext}
     * @return the result of the {@link at.ahammer.boardgame.cdi.RunInGameContext}
     */
    public static <T> T run(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        return BeanProvider.getBean(beanManager, GameContext.class).runInternal(beanManager, runnable);
    }

    public static <T> T run(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        return BeanProvider.getBean(beanManager, GameContext.class).runInternal(beanManager, getGameContextInstance(gameContextId), runnable);
    }

    public static void addNewInstanceInGameContext(BeanManager beanManager, NewInstanceInGameContext newInstanceInGameContext) {
        BeanProvider.getBean(beanManager, NewInstanceInGameContextCache.class).addNewInstanceToGameContext(newInstanceInGameContext);
    }

    public static Set<NewInstanceInGameContext> getNewInstancesInGameContext(BeanManager beanManager) {
        return BeanProvider.getBean(beanManager, NewInstanceInGameContextCache.class).getNewInstancesInGameContext();
    }

    private static Stack<GameContextInstance> getGameContextStack() {
        Stack<GameContextInstance> stack = gameContextStack.get();
        if (stack == null) {
            stack = new Stack<>();
            gameContextStack.set(stack);
        }
        return stack;
    }

    private static GameContextInstance getGameContextInstance(UUID gameContextId) {
        GameContextInstance gameContextInstance = gameContextCache.get(gameContextId);
        if (gameContextInstance == null) {
            throw new GameContextException("No " + GameContext.class.getSimpleName() + " with id " + gameContextId + " available!");
        }
        return gameContextInstance;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return get(contextual, null);
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        BeanInstance<T> instance = getGameContextInstance().get(contextual);
        if (instance != null) {
            // bean is already available in context -> return
            return instance.get();
        }
        if (creationalContext == null) {
            // creationalContext is null -> return null
            return null;
        } else {
            // create and return bean
            return getGameContextInstance().addBeanInstance(contextual, creationalContext);
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

    private GameContextInstance getGameContextInstance() {
        if (getGameContextStack().isEmpty()) {
            throw new GameContextException(new ContextNotActiveException("This context may only be used inside an active " + this.getClass().getName()));
        }
        return getGameContextStack().peek();
    }

    private <T> T runInternal(BeanManager beanManager, GameContextInstance gameContextInstance, RunInGameContext<T> runnable) throws Throwable {
        // remove old GameContexts from cache
        // FIXME not always! -> every xx minutes
        Set<UUID> contextsToDelete = BeanProvider.getBean(beanManager, GameContextCacheDeleteStrategy.class).getContextsToDelete(gameContextCache);
        contextsToDelete.stream().forEach((contextId) -> {
            gameContextCache.remove(contextId);
        });

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

    private <T> T runInternal(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        // create a new GameContextInstance
        UUID gameContextId = UUID.randomUUID();
        GameContextInstance gameContextInstance = new GameContextInstance(gameContextId);
        gameContextCache.put(gameContextId, gameContextInstance);
        // perform the statements in the runnable
        return runInternal(beanManager, gameContextInstance, runnable);
    }
}
