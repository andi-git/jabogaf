package org.jabogaf.core.cdi;

import org.jabogaf.api.cdi.GameContextManager;
import org.jabogaf.core.cdi.bean.BeanWithoutGameScoped;
import org.jabogaf.core.cdi.bean.MyGameContextBean;
import org.jabogaf.core.cdi.bean.MyOtherGameContextBean;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.ArquillianGameContextTest;
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
}
