package at.ahammer.boardgame.core.cdi;

import at.ahammer.boardgame.core.cdi.bean.BeanWithGameScoped;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
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