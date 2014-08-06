package at.ahammer.boardgame.cdi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 03.08.14.
 */
@RunWith(ArquillianGameContext.class)
public class GameContextTest extends BeanManagerProducer {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Test
    public void testComponentInGameContext() {
        Assert.assertNotNull(beanWithGameScoped.getString());
        Assert.assertEquals("i'm in GameContext", beanWithGameScoped.getString());
    }

}