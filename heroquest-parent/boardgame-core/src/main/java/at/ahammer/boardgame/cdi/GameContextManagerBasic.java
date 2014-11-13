package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Basic implementation of {@link at.ahammer.boardgame.cdi.GameContextManager}
 */
@ApplicationScoped
public class GameContextManagerBasic implements GameContextManager {

    @Inject
    private BeanManager beanManager;

    @Override
    public <T> T add(T bean) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public <T> T resolve(T bean) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public <T> T runInGameContext(RunInGameContext<T> runnable) throws Throwable {
        return GameContext.run(beanManager, runnable);
    }
}
