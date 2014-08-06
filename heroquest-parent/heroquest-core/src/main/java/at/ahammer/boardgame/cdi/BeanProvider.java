package at.ahammer.boardgame.cdi;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Get the CDI-cachedBeans via {@link #getBean(javax.enterprise.inject.spi.BeanManager, Class)}. This class will also cache the cachedBeans.
 */
public class BeanProvider {

    private static final Map<Class<?>, Bean<?>> cachedBeans = new HashMap<>();

    private static final Annotation[] emptyAnnotations = new Annotation[0];

    private static Bean<?> getBeanFromCache(BeanManager beanManager, Class<?> clazz) {
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
    public static <T> T getBean(BeanManager beanManager, Class<T> clazz) {
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
}
