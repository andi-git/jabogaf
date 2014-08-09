package at.ahammer.boardgame.cdi;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ahammer on 06.08.2014.
 */
public class GameContextInstance {

    private final UUID id;

    private Instant lastAccess;

    private final Map<Contextual<?>, BeanInstance<?>> contextualMap = new ConcurrentHashMap<>();

    private final Map<Class<?>, Bean<?>> cachedBeans = new HashMap<>();

    private static final Annotation[] emptyAnnotations = new Annotation[0];

    private final Set<NewInstanceInGameContext> newInstancesInGameContext = new HashSet<>();

    public GameContextInstance(UUID id) {
        this.id = id;
        this.lastAccess = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public <T> BeanInstance<T> get(Contextual<T> contextual) {
        this.lastAccess = Instant.now();
        return (BeanInstance<T>) contextualMap.get(contextual);
    }

    public <T> T addBeanInstance(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        BeanInstance<T> beanInstance = new BeanInstance<T>(contextual, creationalContext);
        contextualMap.put(contextual, beanInstance);
        return beanInstance.get();
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    private Bean<?> getBeanFromCache(BeanManager beanManager, Class<?> clazz) {
        Bean<?> result;
        // if the bean is not in the cache, create it and put it into the cache
        if ((result = cachedBeans.get(clazz)) == null) {
            Set<Bean<?>> beans = beanManager.getBeans(clazz, emptyAnnotations);
            result = beanManager.resolve(beans);
            cachedBeans.put(clazz, result);
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
    public <T> T getBean(BeanManager beanManager, Class<T> clazz) {
        try {
            Bean<?> bean = getBeanFromCache(beanManager, clazz);
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

    public void addNewInstanceToGameContext(NewInstanceInGameContext newInstanceInGameContext) {
        newInstancesInGameContext.add(newInstanceInGameContext);
    }

    public Set<NewInstanceInGameContext> getNewInstancesInGameContext() {
        return newInstancesInGameContext;
    }

}
