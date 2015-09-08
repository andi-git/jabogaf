package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextInstanceProvider;
import org.jabogaf.util.loader.ServiceLoader;
import org.jabogaf.util.log.LogProducer;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Helper-class to resolve CDI-Fields (annotated with {@link javax.inject.Inject}) in a bean.
 */
@ApplicationScoped
public class CdiFieldResolver {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private GameContextInstanceProvider gameContextInstanceProvider;

    public <T> T resolve(T bean, Class<?> clazz) {
        // check every field
        for (Field field : clazz.getDeclaredFields()) {
            // if the annotation @Inject is available, inject the cdi-bean
            if (field.getAnnotation(Inject.class) != null) {
                if (field.getType() == Logger.class) {
                    // set logger without cdi
                    setField(bean, field, new LogProducer().produce(clazz));
                } else {
                    // get possible qualifiers
                    Annotation qualifier = getQualifier(field);
                    log.debug("  inject " + field.getType().getName() + " to field " + field.getName() + (qualifier != null ? (", qualifier " + qualifier) : ""));
                    // get the cdi-bean from the GameContext
                    setField(bean, field, gameContextInstanceProvider.getCurrentGameContextInstance().getFromDynamicContext(field.getType(), qualifier));
                }
            }
        }
        if (clazz.getSuperclass() != null) {
            // do the same for all fields of the superclass
            resolve(bean, clazz.getSuperclass());
        }
        return bean;
    }

    private <T> void setField(T bean, Field field, Object object) {
        field.setAccessible(true);
        try {
            // set the cdi-bean
            field.set(bean, object);
        } catch (IllegalAccessException e) {
            log.error("unable to access " + field, e);
        }
    }

    private Annotation getQualifier(Field field) {
        Annotation qualifier = null;
        // check all annotations of the field
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            // check all annotations of the annotation
            for (Annotation metaAnnotation : annotation.annotationType().getDeclaredAnnotations()) {
                // check if it is a qualifier
                if (metaAnnotation.annotationType() == Qualifier.class) {
                    qualifier = annotation;
                    break;
                }
            }
            if (qualifier != null) {
                break;
            }
        }
        return qualifier;
    }
}
