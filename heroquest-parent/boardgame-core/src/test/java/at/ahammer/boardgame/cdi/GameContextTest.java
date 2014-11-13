package at.ahammer.boardgame.cdi;

import at.ahammer.boardgame.test.ArquillianGameContext;
import at.ahammer.boardgame.test.ArquillianGameContextTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class GameContextTest extends ArquillianGameContextTest {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Test
    public void testComponentInGameContext() throws Throwable {
        Assert.assertNotNull(beanWithGameScoped.getString());
        Assert.assertEquals("i'm in GameContext", beanWithGameScoped.getString());
    }
}