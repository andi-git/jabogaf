package at.ahammer.boardgame.test.util;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * This class has the {@link BeanManager} injected and all test-methods will run in an {@link at.ahammer.boardgame.cdi.GameContext}.
 */
public abstract class ArquillianGameContextTest {

    @Deployment
    public static WebArchive deploy() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war").//
                addAsWebInfResource("META-INF/beans.xml").//
                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/services/javax.enterprise.inject.spi.Extension").//
                addPackages(true, "at.ahammer");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

    @Inject
    private BeanManager beanManager;

    public BeanManager getBeanManager() {
        return beanManager;
    }
}
