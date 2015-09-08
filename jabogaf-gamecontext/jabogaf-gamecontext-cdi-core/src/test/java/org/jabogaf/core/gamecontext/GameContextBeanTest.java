package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.core.gamecontext.bean.MyGameContextBean;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class GameContextBeanTest extends ArquillianGameContextTest {

    @Inject
    private GameContextCache gameContextCache;

    @Test
    public void testNewInstanceInGameContext() {
        Assert.assertEquals(0, gameContextCache.getCurrentGameContextInstance().getGameContextBeans().size());
        GameContextBean bean = new MyGameContextBean("id");
        Assert.assertEquals(1, gameContextCache.getCurrentGameContextInstance().getGameContextBeans().size());
        Assert.assertTrue(gameContextCache.getCurrentGameContextInstance().getGameContextBeans().contains(bean));
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
