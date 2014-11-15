package at.ahammer.boardgame.core.cdi;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.cdi.RunInGameContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;

/**
 * CDI-Wrapper for {@link at.ahammer.boardgame.api.cdi.GameContextManager}.
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
}
