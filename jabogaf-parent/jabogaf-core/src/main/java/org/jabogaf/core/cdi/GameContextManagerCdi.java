package org.jabogaf.core.cdi;

import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.api.cdi.GameContextManager;
import org.jabogaf.api.cdi.RunInGameContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import java.util.Set;

/**
 * CDI-Wrapper for {@link org.jabogaf.api.cdi.GameContextManager}.
 */
@ApplicationScoped
public class GameContextManagerCdi implements GameContextManager {

    private final GameContextManager gameContextManager = new GameContextManagerBasic();

    @Override
    public <T extends GameContextBean> T add(T bean) {
        return gameContextManager.add(bean);
    }

    @Override
    public <T extends GameContextBean> T add(T bean, String id) {
        return gameContextManager.add(bean, id);
    }

    @Override
    public <T> T resolve(T bean) {
        return gameContextManager.resolve(bean);
    }

    @Override
    public <T> T runInGameContext(BeanManager beanManager, RunInGameContext<T> runnable) throws Throwable {
        return gameContextManager.runInGameContext(beanManager, runnable);
    }

    @Override
    public <T> T getGameContextBean(Class<T> type, String id) {
        return gameContextManager.getGameContextBean(type, id);
    }

    @Override
    public Set<GameContextBean> getGameContextBeans() {
        return gameContextManager.getGameContextBeans();
    }

    @Override
    public <T extends GameContextBean> Set<T> getGameContextBeans(Class<T> type) {
        return gameContextManager.getGameContextBeans(type);
    }

    @Override
    public GameContextBean getGameContextBean(String id) {
        return gameContextManager.getGameContextBean(id);
    }

    @Override
    public void fireGameStateChangedEvent() {
        gameContextManager.fireGameStateChangedEvent();
    }
}
