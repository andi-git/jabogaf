package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;

/**
 * CDI-Wrapper for {@link GameContextManager}.
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
}
