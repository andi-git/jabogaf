package at.ahammer.boardgame.core.cdi;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.cdi.RunInGameContext;
import at.ahammer.boardgame.core.state.GameStateChanged;
import at.ahammer.boardgame.util.log.LogProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Typed;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Basic implementation of {@link at.ahammer.boardgame.api.cdi.GameContextManager}
 */
@Typed
public class GameContextManagerBasic implements GameContextManager {

    private Logger log = LoggerFactory.getLogger(GameContextManagerBasic.class);

    @Override
    public <T extends GameContextBean> T add(T bean) {
        return add(bean, bean.getId());
    }

    @Override
    public <T extends GameContextBean> T add(T bean, String id) {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("id must have a value!");
        }
        if (GameContext.current().getGameContextBeans().stream().filter(b -> b.getId().equals(id)).count() > 0) {
            throw new IllegalStateException("id '" + id + "' already in use");
        }
        return resolve(GameContext.addGameContextBean(bean));
    }

    @Override
    public <T> T resolve(T bean) {
        log.debug("resolve fields for " + bean.getClass());
        return resolve(bean, bean.getClass());
    }

    private <T> T resolve(T bean, Class<?> clazz) {
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
                    setField(bean, field, GameContext.current().getFromDynamicContext(field.getType(), qualifier));
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

    @Override
    public <T> T runInGameContext(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        return GameContext.run(beanManager, runnable);
    }

    @Override
    public <T> T getGameContextBean(Class<T> type, String id) {
        return GameContext.current().getGameContextBean(type, id);
    }

    @Override
    public Set<GameContextBean> getGameContextBeans() {
        return GameContext.current().getGameContextBeans();
    }

    @Override
    public <T extends GameContextBean> Set<T> getGameContextBeans(Class<T> type) {
        return GameContext.current().getGameContextBeans(type);
    }

    @Override
    public GameContextBean getGameContextBean(String id) {
        return GameContext.current().getGameContextBean(id);
    }

    public void fireGameStateChangedEvent() {
        GameContext.current().getFromDynamicContext(HelperToFireGameStateChangedEvent.class).fire();
    }

    @GameScoped
    public static class HelperToFireGameStateChangedEvent {

        @Inject
        private Event<GameStateChanged> gameStateChangedEvent;

        public void fire() {
            gameStateChangedEvent.fire(new GameStateChanged(null));
        }
    }
}
