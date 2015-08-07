package org.jabogaf.core.cdi;

import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.core.cdi.bean.MyGameContextBean;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ArquillianGameContext.class)
public class GameContextBeanTest extends ArquillianGameContextTest {

    @Test
    public void testNewInstanceInGameContext() {
        Assert.assertEquals(0, GameContext.current().getGameContextBeans().size());
        GameContextBean bean = new MyGameContextBean("id");
        Assert.assertEquals(1, GameContext.current().getGameContextBeans().size());
        Assert.assertTrue(GameContext.current().getGameContextBeans().contains(bean));
    }

    @Test(expected = IllegalStateException.class)
    public void testNewInstanceInGameContextDuplicateId() {
        new MyGameContextBean("id");
        new MyGameContextBean("id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewInstanceInGameContextIllegalId() {
        new MyGameContextBean(null);
    }
}
