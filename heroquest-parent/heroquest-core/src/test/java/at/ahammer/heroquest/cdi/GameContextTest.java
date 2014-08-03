package at.ahammer.heroquest.cdi;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * Created by andreas on 03.08.14.
 */
@RunWith(ArquillianGameContext.class)
public class GameContextTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, GameContextTest.class.getSimpleName() + ".jar").//
                addPackage(GameContext.class.getPackage()).//
                addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension", "META-INF/serivces/javax.enterprise.inject.spi.Extension");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Test
    public void testComponentInGameContext() {
        Assert.assertNotNull(beanWithGameScoped.getString());
        Assert.assertEquals("i'm in GameContext", beanWithGameScoped.getString());
    }
}
