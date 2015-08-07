package org.jabogaf.test.cdi;

import org.jabogaf.api.cdi.AlternativesInGameContext;
import org.jabogaf.api.cdi.GameContextManager;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * This class has the {@link BeanManager} injected and all test-methods will run in an
 * {@link org.jabogaf.api.cdi.GameScoped}.
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
