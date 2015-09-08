package org.jabogaf.test.gamecontext;

import org.jabogaf.api.gamecontext.AlternativesInGameContext;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.gamecontext.GameScoped;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * This class has the {@link BeanManager} injected and all test-methods will run in an {@link GameScoped}.
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
