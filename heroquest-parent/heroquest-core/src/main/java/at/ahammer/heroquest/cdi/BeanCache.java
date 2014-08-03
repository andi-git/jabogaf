package at.ahammer.heroquest.cdi;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.spi.Bean;

public class BeanCache {

    private static final Map<Class<?>, Bean<?>> beans = new HashMap<>();

    private static final Annotation[] emptyAnnotations = new Annotation[0];

    public static Bean<?> getBean(Class<?> clazz) {
        Bean<?> result;
        if ((result = BeanCache.beans.get(clazz)) == null) {
            Set<Bean<?>> beans = BeanManagerProvider.getBeanManager().getBeans(clazz, emptyAnnotations);
            result = BeanManagerProvider.getBeanManager().resolve(beans);
            BeanCache.beans.put(clazz, result);
        }
        return result;
    }
}
