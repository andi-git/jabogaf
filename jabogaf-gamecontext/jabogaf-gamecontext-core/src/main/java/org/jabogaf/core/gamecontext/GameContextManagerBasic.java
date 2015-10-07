package org.jabogaf.core.gamecontext;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.gamecontext.*;
import org.jabogaf.core.event.GameStateChangedEventBean;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * Basic implementation of {@link GameContextManager}
 */
@ApplicationScoped
public class GameContextManagerBasic implements GameContextManager {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private CdiFieldResolver cdiFieldResolver;

    @Inject
    private GameContextInstanceProvider gameContextInstanceProvider;

    @Inject
    private GameContextRunner gameContextRunner;

    @Override
    public <T extends GameContextBean> T add(T bean) {
        return add(bean, bean.getId());
    }

    @Override
    public <T extends GameContextBean> T add(T bean, String id) {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("id must have a value!");
        }
        if (getCurrentGameContextInstance().getGameContextBeans().stream().filter(b -> b.getId().equals(id)).count() > 0) {
            throw new IllegalStateException("id '" + id + "' already in use");
        }
        T beanWithInjectionsResolved = resolve(getCurrentGameContextInstance().addGameContextBean(bean));
        if (beanWithInjectionsResolved instanceof GameContextBeanWithState) {
            fireGameStateChangedEvent((GameContextBeanWithState) beanWithInjectionsResolved);
        }
        return beanWithInjectionsResolved;
    }

    @Override
    public <T> T resolve(T bean) {
        log.debug("resolve fields for " + bean.getClass());
        return cdiFieldResolver.resolve(bean, bean.getClass());
    }

    @Override
    public <T> T runInGameContext(BeanManager beanManager, RunInGameContext<T> runnable) {
        return runInGameContext(null, beanManager, runnable);
    }

    @Override
    public <T> T runInGameContext(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable) {
        return gameContextRunner.runInGameContext(gameContextId, beanManager, runnable);
    }

    @Override
    public UUID getId() {
        return getCurrentGameContextInstance().getId();
    }

    @Override
    public <T> T addToCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        return getCurrentGameContextInstance().addToCreationalContext(bean, creationalContext);
    }

    @Override
    public <T> boolean isAvailableInCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        return getCurrentGameContextInstance().isAvailableInCreationalContext(bean, creationalContext);
    }

    @Override
    public <T> T getFromCreationalContext(Bean<T> bean, CreationalContext<T> creationalContext) {
        return getCurrentGameContextInstance().getFromCreationalContext(bean, creationalContext);
    }

    @Override
    public Instant getLastAccess() {
        return getCurrentGameContextInstance().getLastAccess();
    }

    @Override
    public <T> T getFromDynamicContext(Class<T> type, Annotation... qualifiers) {
        return getCurrentGameContextInstance().getFromDynamicContext(type, qualifiers);
    }

    @Override
    public <T extends GameContextBean> T addGameContextBean(T bean) {
        return getCurrentGameContextInstance().addGameContextBean(bean);
    }

    @Override
    public <T> T getGameContextBean(Class<T> type, String id) {
        return getCurrentGameContextInstance().getGameContextBean(type, id);
    }

    @Override
    public Set<GameContextBean> getGameContextBeans() {
        return getCurrentGameContextInstance().getGameContextBeans();
    }

    @Override
    public <T extends GameContextBean> Set<T> getGameContextBeans(Class<T> type) {
        return getCurrentGameContextInstance().getGameContextBeans(type);
    }

    @Override
    public GameContextBean getGameContextBean(String id) {
        return getCurrentGameContextInstance().getGameContextBean(id);
    }

    @Override
    public <T extends GameContextBean> T getGameContextBean(Class<T> type) {
        return getCurrentGameContextInstance().getGameContextBean(type);
    }

    @Override
    public <T extends GameContextBeanWithState> void fireGameStateChangedEvent(T gameContextBean) {
        getCurrentGameContextInstance().getFromDynamicContext(HelperToFireGameStateChangedEvent.class).fire(gameContextBean);
    }

    private GameContextInstance getCurrentGameContextInstance() {
        return gameContextInstanceProvider.getCurrentGameContextInstance();
    }

    @Dependent
    public static class HelperToFireGameStateChangedEvent {

        @Inject
        private Event<GameStateChangedEvent> gameStateChangedEvent;

        public <T extends GameContextBeanWithState> void fire(T gameContextBean) {
            gameStateChangedEvent.fire(new GameStateChangedEventBean(Instant.now(), gameContextBean));
        }
    }
}
