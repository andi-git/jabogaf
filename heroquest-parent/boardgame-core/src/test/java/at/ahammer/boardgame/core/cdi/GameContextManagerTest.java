package at.ahammer.boardgame.core.cdi;

import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.core.cdi.bean.BeanWithoutGameScoped;
import at.ahammer.boardgame.core.cdi.bean.MyGameContextBean;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class GameContextManagerTest extends ArquillianGameContextTest {

    @Inject
    private GameContextManager gameContextManager;

    @Test
    public void testResolveInjections() {
        BeanWithoutGameScoped bean = new BeanWithoutGameScoped();
        Assert.assertNull(bean.getBeanWithGameScoped());
        Assert.assertNull(bean.getBeanWithGameScopedInSuperclass());
        gameContextManager.resolve(bean);
        Assert.assertNotNull(bean.getBeanWithGameScoped());
        Assert.assertNotNull(bean.getBeanWithGameScopedInSuperclass());
        Assert.assertEquals("i'm in GameContext", bean.getBeanWithGameScoped().getString());
        Assert.assertEquals("qualifier", bean.getBeanWithGameScopedInSuperclass().getString());
    }

    @Test
    public void testGameContextBean() {
        MyGameContextBean myGameContextBean = new MyGameContextBean("myid");
        Assert.assertEquals("GameContextBean", myGameContextBean.getString());
        Assert.assertNotNull(myGameContextBean.getBeanWithGameScoped());
        Assert.assertEquals("i'm in GameContext", myGameContextBean.getBeanWithGameScoped().getString());
    }
}
