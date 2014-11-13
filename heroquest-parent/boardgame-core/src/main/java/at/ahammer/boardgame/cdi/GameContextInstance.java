package at.ahammer.boardgame.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.*;

public class GameContextInstance {

    private static final Logger log = LoggerFactory.getLogger(GameContextInstance.class);

    private static final Annotation[] emptyAnnotations = new Annotation[0];

    private static final AnnotationLiteralAlternativeInGameContext annotationLiteralAlternativeInGameContext = new AnnotationLiteralAlternativeInGameContext();

    private final UUID id;
    private final CacheForDeploymentBeans cacheForDeploymentBeans;
    private final CacheForDynamicBeans cacheForDynamicBeans;
    private final Set<NewInstanceInGameContext> newInstancesInGameContext = new HashSet<>();
    private Instant lastAccess;

    public GameContextInstance(UUID id, BeanManager beanManager) {
        this.id = id;
        this.lastAccess = Instant.now();
        this.cacheForDeploymentBeans = new CacheForDeploymentBeans(beanManager);
        this.cacheForDynamicBeans = new CacheForDynamicBeans(beanManager);
    }

    public UUID getId() {
        return id;
    }

    public <T> T addToCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.addBeanInstance(bean, creationalContext);
    }

    public <T> boolean isAvailableInCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.get(bean, creationalContext) != null;
    }

    public <T> T getFromCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        this.lastAccess = Instant.now();
        return cacheForDeploymentBeans.get(bean, creationalContext).get();
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    /**
     * Returns the bean (i.e. a proxy to any actual instance) used by the CDI container for injection of instances of
     * the given type.
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

    public NewInstanceInGameContext getNewInstanceInGameContext(String id) {
        return getNewInstancesInGameContext().stream().filter(b -> b.getId().equals(id)).findFirst().get();
    }

    public <T> T getNewInstanceInGameContext(Class<T> clazz, String id) {
        return (T) getNewInstancesInGameContext().stream().filter(b -> b.getId().equals(id)).findFirst().get();
    }

    public <T> T getNewInstanceInGameContext(Class<T> clazz) {
        for (NewInstanceInGameContext newInstanceInGameContext : GameContext.current().getNewInstancesInGameContext()) {
            if (clazz.isAssignableFrom(newInstanceInGameContext.getClass())) {
                // get only first match
                return (T) newInstanceInGameContext;
            }
        }
        return null;
    }

    private static class CacheForDeploymentBeans {

        private final Map<Bean<?>, BeanInstance<?>> cacheForDeploymentBeans = Collections.synchronizedMap(new HashMap<>());

        private final BeanManager beanManager;

        public CacheForDeploymentBeans(BeanManager beanManager) {
            this.beanManager = beanManager;
        }

        public <T> BeanInstance<T> get(Bean<T> bean, CreationalContext<T> creationalContext) {
            return (BeanInstance<T>) cacheForDeploymentBeans.get(replaceWithAlternativeIfActivated(bean, creationalContext));
        }

        public <T> T addBeanInstance(Bean<T> bean, CreationalContext<T> creationalContext) {
            bean = replaceWithAlternativeIfActivated(bean, creationalContext);
            BeanInstance<T> beanInstance = new BeanInstance<T>(bean, creationalContext);
            cacheForDeploymentBeans.put(bean, beanInstance);
            return beanInstance.get();
        }

        private <T> Bean<T> replaceWithAlternativeIfActivated(Bean<T> bean, CreationalContext<T> creationalContext) {
            if (bean != null && creationalContext != null) {
                log.debug("registered alternatives: " + getAlternativesInGameContext(creationalContext).getAlternatives());
                if (getAlternativesInGameContext(creationalContext).contains(bean.getBeanClass())) {
                    Bean<T> beanBefore = bean;
                    bean = (Bean<T>) beanManager.getBeans(bean.getBeanClass(), annotationLiteralAlternativeInGameContext).iterator().next();
                    log.info("use alternative: " + bean + " for " + beanBefore);
                }
            }
            return bean;
        }

        private <T> AlternativesInGameContext getAlternativesInGameContext(CreationalContext<T> creationalContext) {
            Bean bean = beanManager.getBeans(AlternativesInGameContext.class).iterator().next();
            return (AlternativesInGameContext) beanManager.getReference(bean, AlternativesInGameContext.class, creationalContext);
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
         * Returns the bean (i.e. a proxy to any actual instance) used by the CDI container for injection of instances
         * of the given type.
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

        private final Bean<T> bean;

        private final CreationalContext<T> creationalContext;

        private final T instance;

        public BeanInstance(Bean<T> bean, CreationalContext<T> creationalContext) {
            this(bean, creationalContext, bean.create(creationalContext));
        }

        public BeanInstance(Bean<T> bean, CreationalContext<T> creationalContext, T instance) {
            this.bean = bean;
            this.creationalContext = creationalContext;
            this.instance = instance;
        }

        public void destroy() {
            bean.destroy(this.instance, this.creationalContext);
        }

        public T get() {
            return instance;
        }

        protected Bean<T> getBean() {
            return bean;
        }

        protected CreationalContext<T> getCreationalContext() {
            return this.creationalContext;
        }

    }

    private static final class AnnotationLiteralAlternativeInGameContext extends AnnotationLiteral<AlternativeInGameContext> {

    }
}
