package at.ahammer.boardgame.test;

import at.ahammer.boardgame.cdi.AlternativesInGameContext;
import at.ahammer.boardgame.cdi.GameContextManager;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * This class has the {@link javax.enterprise.inject.spi.BeanManager} injected and all test-methods will run in an
 * {@link at.ahammer.boardgame.cdi.GameScoped}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ArquillianGameContextTest {

    @Inject
    private BeanManager beanManager;

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private AlternativesInGameContext alternativesInGameContext;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public GameContextManager getGameContextManager() {
        return gameContextManager;
    }

    public AlternativesInGameContext getAlternativesInGameContext() {
        return alternativesInGameContext;
    }
}
