package at.ahammer.boardgame.cdi;

import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 09.08.14.
 */
@RunWith(ArquillianGameContext.class)
public class NewInstanceInGameContextBeanTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    @Test
    public void testNewInstanceInGameContext() {
        Assert.assertEquals(0, GameContext.current().getNewInstancesInGameContext().size());
        NewInstanceInGameContextBean bean = new NewInstanceInGameContextBean();
        Assert.assertEquals(1, GameContext.current().getNewInstancesInGameContext().size());
        Assert.assertTrue(GameContext.current().getNewInstancesInGameContext().contains(bean));
    }

    @Test(expected = IllegalStateException.class)
    public void testNewInstanceInGameContextDuplicateId() {
        new NewInstanceInGameContextBean();
        new NewInstanceInGameContextBean();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewInstanceInGameContextIllegalId() {
        new NewInstanceInGameContextBean(null);
    }
}
