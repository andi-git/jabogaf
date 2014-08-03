package at.ahammer.heroquest.cdi;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * This class represents a CDI-context for a game. The context has to be
 * started at the begin of a game and will (automatically) be
 * destroyed at the end of a game. All components with the
 * annotation {@link at.ahammer.heroquest.cdi.GameScoped} will be unique in this context.<br>
 * <br>
 * To start the context use
 * {@link #run(java.util.concurrent.Callable)} of {@link #run(Runnable)}.
 *
 * @author Andreas
 */
@Singleton
public class GameContext implements Context {

    private static final ThreadLocal<Stack<Map<Contextual<?>, BeanInstance<?>>>> contextualMaps = new ThreadLocal<>();

    private static Set<NewInstanceInGameContext> newInstancesInGameContext = new HashSet<>();

    private static Stack<Map<Contextual<?>, BeanInstance<?>>> getInitializedStack() {
        Stack<Map<Contextual<?>, BeanInstance<?>>> stack = contextualMaps.get();
        if (stack == null) {
            stack = new Stack<>();
            contextualMaps.set(stack);
        }
        return stack;
    }

    /**
     * only for tests
     */
    public static void start() {
        getInitializedStack().push(new HashMap<Contextual<?>, BeanInstance<?>>());
    }

    /**
     * only for tests
     */
    public static void stop() {
        getInitializedStack().pop();
    }

    /**
     * The assigned code will run within a new {@link at.ahammer.heroquest.cdi.GameContext}. The
     * context will be started automatically and will be destroyed afterwards.
     *
     * @param runnable - the {@link Runnable} which code must run within a
     *                 {@link at.ahammer.heroquest.cdi.GameContext}
     */
    public static void run(Runnable runnable) {
        getBeanByName(GameContext.class).runInternal(Executors.callable(runnable));
    }

    /**
     * The assigned code will run within a new {@link at.ahammer.heroquest.cdi.GameContext}. The
     * context will be started automatically and will be destroyed afterwards.
     *
     * @param runnable - the {@link java.util.concurrent.Callable} which code must run within a
     *                 {@link at.ahammer.heroquest.cdi.GameContext}
     * @return the result of the {@link java.util.concurrent.Callable}
     */
    public static <T> T run(Callable<T> runnable) {
        return getBeanByName(GameContext.class).runInternal(runnable);
    }

    /**
     * Returns the bean (i.e. a proxy to any actual instance) used by the CDI
     * container for injection of instances of the given type.
     *
     * @param <T>
     * @param clazz
     * @return the bean for the given class.
     */
    static <T> T getBeanByName(Class<T> clazz) {
        try {
            Bean<?> bean = BeanCache.getBean(clazz);
            if (bean != null) {
                CreationalContext<?> ctx = BeanManagerProvider.getBeanManager().createCreationalContext(bean);
                Object o = BeanManagerProvider.getBeanManager().getReference(bean, clazz, ctx);
                return clazz.cast(o);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return get(contextual, null);
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        Map<Contextual<?>, BeanInstance<?>> instances = getContextualMap(creationalContext);
        BeanInstance<T> instance = getInstance(contextual, instances);
        if (instance != null) {
            return instance.get();
        }
        if (creationalContext == null) {
            return null;
        }
        instance = new BeanInstance<T>(contextual, creationalContext);
        instances.put(contextual, instance);
        return instance.get();
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return GameScoped.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> BeanInstance<T> getInstance(Contextual<T> requestedContextual, Map<Contextual<?>, BeanInstance<?>> instances) {
        return (BeanInstance<T>) instances.get(requestedContextual);
    }

    private Map<Contextual<?>, BeanInstance<?>> getContextualMap(CreationalContext<?> creationalContext) {
        Stack<Map<Contextual<?>, BeanInstance<?>>> stack = contextualMaps.get();
        if (stack == null || stack.isEmpty()) {
            throw new ContextNotActiveException("This context may only be used inside an active " + this.getClass().getName());
        }
        return stack.peek();
    }

    private <V> V runInternal(Callable<V> runnable) {
        Stack<Map<Contextual<?>, BeanInstance<?>>> stack = getInitializedStack();
        stack.push(new HashMap<Contextual<?>, BeanInstance<?>>());
        try {
            return runnable.call();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            stack.pop();
        }
    }

    public static void addNewInstanceInGameContext(NewInstanceInGameContext newInstanceInGameContext) {
        getBeanByName(NewInstanceInGameContextCache.class).addNewInstanceToGameContext(newInstanceInGameContext);
    }

    public static Set<NewInstanceInGameContext> getNewInstancesInGameContext() {
        return getBeanByName(NewInstanceInGameContextCache.class).getNewInstancesInGameContext();
    }
}
