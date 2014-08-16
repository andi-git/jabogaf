package at.ahammer.boardgame.cdi;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.*;

/**
 * Created by ahammer on 06.08.2014.
 */
public class GameContextInstance {

    private static final Annotation[] emptyAnnotations = new Annotation[0];

    private final UUID id;

    private Instant lastAccess;

    private final CacheForDeploymentBeans cacheForDeploymentBeans;

    private final CacheForDynamicBeans cacheForDynamicBeans;

    private final Set<NewInstanceInGameContext> newInstancesInGameContext = new HashSet<>();

    public GameContextInstance(UUID id, BeanManager beanManager) {
        this.id = id;
        this.lastAccess = Instant.now();
        this.cacheForDeploymentBeans = new CacheForDeploymentBeans();
        this.cacheForDynamicBeans = new CacheForDynamicBeans(beanManager);
    }

    public UUID getId() {
        return id;
    }

    public <T> T addToCreationalContext(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.addBeanInstance(contextual, creationalContext);
    }

    public boolean isAvailableInCreationalContext(Contextual<?> contextual) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.get(contextual) != null;
    }

    public <T> T getFromCreationalContext(Contextual<T> contextual) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.get(contextual).get();
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    /**
     * Returns the bean (i.e. a proxy to any actual instance) used by the CDI
     * container for injection of instances of the given type.
     *
     * @param <T>
     * @param clazz
     * @return the bean for the given class.
     */
    public <T> T getFromDynamicContext(Class<T> clazz) {
        this.lastAccess = Instant.now();
        return cacheForDynamicBeans.getBean(clazz);
    }

    public void addNewInstanceToGameContext(NewInstanceInGameContext newInstanceInGameContext) {
        this.lastAccess = Instant.now();
        newInstancesInGameContext.add(newInstanceInGameContext);
    }

    public Set<NewInstanceInGameContext> getNewInstancesInGameContext() {
        this.lastAccess = Instant.now();
        return newInstancesInGameContext;
    }


    private static class CacheForDeploymentBeans {

        private final Map<Contextual<?>, BeanInstance<?>> cacheForDeploymentBeans = Collections.synchronizedMap(new HashMap<>());

        public <T> BeanInstance<T> get(Contextual<T> contextual) {
            return (BeanInstance<T>) cacheForDeploymentBeans.get(contextual);
        }

        public <T> T addBeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext) {
            BeanInstance<T> beanInstance = new BeanInstance<T>(contextual, creationalContext);
            cacheForDeploymentBeans.put(contextual, beanInstance);
            return beanInstance.get();
        }
    }

    private static class CacheForDynamicBeans {

        private final BeanManager beanManager;

        private final Map<Class<?>, Bean<?>> cacheForDynamicBeans = Collections.synchronizedMap(new HashMap<>());

        private CacheForDynamicBeans(BeanManager beanManager) {
            this.beanManager = beanManager;
        }

        private Bean<?> getBeanFromCache(Class<?> clazz) {
            Bean<?> result;
            // if the bean is not in the cache, create it and put it into the cache
            if ((result = cacheForDynamicBeans.get(clazz)) == null) {
                Set<Bean<?>> beans = beanManager.getBeans(clazz, emptyAnnotations);
                result = beanManager.resolve(beans);
                cacheForDynamicBeans.put(clazz, result);
            }
            return result;
        }

        /**
         * Returns the bean (i.e. a proxy to any actual instance) used by the CDI
         * container for injection of instances of the given type.
         *
         * @param <T>
         * @param clazz
         * @return the bean for the given class.
         */
        public <T> T getBean(Class<T> clazz) {
            try {
                Bean<?> bean = getBeanFromCache(clazz);
                if (bean != null) {
                    CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
                    Object o = beanManager.getReference(bean, clazz, ctx);
                    return clazz.cast(o);
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Representation of a concrete {@link javax.enterprise.inject.spi.Bean}.
     */
    public static class BeanInstance<T> {

        private final Contextual<T> contextual;

        private final CreationalContext<T> creationalContext;

        private final T instance;

        public BeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext) {
            this(contextual, creationalContext, contextual.create(creationalContext));
        }

        public BeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext, T instance) {
            this.contextual = contextual;
            this.creationalContext = creationalContext;
            this.instance = instance;
        }

        public void destroy() {
            this.contextual.destroy(this.instance, this.creationalContext);
        }

        public T get() {
            return this.instance;
        }

        protected Contextual<T> getContextual() {
            return this.contextual;
        }

        protected CreationalContext<T> getCreationalContext() {
            return this.creationalContext;
        }

    }
}
