package org.jabogaf.core.cdi;

import org.jabogaf.core.cdi.bean.BeanWithGameScoped;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.ArquillianGameContextTest;
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