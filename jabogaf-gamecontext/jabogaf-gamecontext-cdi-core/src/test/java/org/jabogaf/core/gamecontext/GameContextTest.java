package org.jabogaf.core.gamecontext;

import org.jabogaf.core.gamecontext.bean.BeanWithGameScoped;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
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