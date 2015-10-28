package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextException;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.core.gamecontext.bean.BeanWithoutGameScoped;
import org.jabogaf.core.gamecontext.bean.MyGameContextBean;
import org.jabogaf.core.gamecontext.bean.MyOtherGameContextBean;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(ArquillianGameContext.class)
public class GameContextManagerTest extends ArquillianGameContextTest {

    @Inject
    private GameContextManager gameContextManager;

    @Test
    public void testResolveInjections() {
        BeanWithoutGameScoped bean = new BeanWithoutGameScoped();
        assertNull(bean.getBeanWithGameScoped());
        assertNull(bean.getBeanWithGameScopedInSuperclass());
        gameContextManager.resolve(bean);
        assertNotNull(bean.getBeanWithGameScoped());
        assertNotNull(bean.getBeanWithGameScopedInSuperclass());
        assertEquals("i'm in GameContext", bean.getBeanWithGameScoped().getString());
        assertEquals("qualifier", bean.getBeanWithGameScopedInSuperclass().getString());
    }

    @Test
    public void testGameContextBean() {
        MyGameContextBean myGameContextBean = new MyGameContextBean("myid");
        assertEquals("GameContextBean", myGameContextBean.getString());
        assertNotNull(myGameContextBean.getBeanWithGameScoped());
        assertEquals("i'm in GameContext", myGameContextBean.getBeanWithGameScoped().getString());
    }

    @Test
    public void testGetGameContextBeans() {
        new MyGameContextBean("myid1");
        new MyGameContextBean("myid2");
        assertEquals(2, gameContextManager.getGameContextBeans().size());
    }

    @Test
    public void testGetGameContextBeansByType() {
        new MyGameContextBean("myid1");
        new MyGameContextBean("myid2");
        new MyOtherGameContextBean("myid3");
        assertEquals(3, gameContextManager.getGameContextBeans().size());
        assertEquals(2, gameContextManager.getGameContextBeans(MyGameContextBean.class).size());
        assertEquals(1, gameContextManager.getGameContextBeans(MyOtherGameContextBean.class).size());
    }

    @Test
    public void testGetGameContextBeanById() {
        new MyGameContextBean("myid1");
        new MyGameContextBean("myid2");
        assertNotNull(gameContextManager.getGameContextBean("myid1"));
        assertNotNull(gameContextManager.getGameContextBean("myid2"));
        assertNull(gameContextManager.getGameContextBean("myid3"));
    }

    @Test
    public void testGetGameContextBeanByTypeAndId() {
        new MyGameContextBean("myid1");
        new MyGameContextBean("myid2");
        MyGameContextBean myGameContextBean = gameContextManager.getGameContextBean(MyGameContextBean.class, "myid1");
        assertEquals("myid1", myGameContextBean.getId());
        MyOtherGameContextBean myOtherGameContextBean = gameContextManager.getGameContextBean(MyOtherGameContextBean.class, "myid2");
        assertNull(gameContextManager.getGameContextBean(MyOtherGameContextBean.class, "myid2"));
    }

    @Test(expected = GameContextException.class)
    public void testGetOneSpecificGameContextBeanByTypeButNoneAvailable() {
        gameContextManager.getGameContextBean(MyGameContextBean.class);
    }

    @Test(expected = GameContextException.class)
    public void testGetOneSpecificGameContextBeanByTypeButMultipleAvailable() {
        new MyGameContextBean("myid1");
        new MyGameContextBean("myid2");
        gameContextManager.getGameContextBean(MyGameContextBean.class);
    }

    @Test
    public void testGetOneSpecificGameContextBeanByType() {
        new MyGameContextBean("myid1");
        assertEquals("myid1", gameContextManager.getGameContextBean(MyGameContextBean.class).getId());
    }
}
