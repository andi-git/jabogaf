package org.jabogaf.core.cdi;

import org.jabogaf.api.cdi.AlternativeInGameContext;
import org.jabogaf.core.cdi.bean.BeanWithGameScoped;
import org.jabogaf.core.cdi.bean.BeanWithGameScopedAlternative;
import org.jabogaf.core.test.ActivateAlternatives;
import org.jabogaf.core.test.ArquillianGameContext;
import org.jabogaf.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
@ActivateAlternatives(BeanWithGameScopedAlternative.class)
public class GameContextAlternativeTest extends ArquillianGameContextTest {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Inject
    @AlternativeInGameContext
    private BeanWithGameScoped beanWithGameScopedAlternative;

    @Test
    public void testAlternativeInGameContext() throws Throwable {
        beanWithGameScoped.setCounter(0);
        Assert.assertNotNull(beanWithGameScoped.getString());
        beanWithGameScoped.setCounter(1);
        Assert.assertEquals(1, beanWithGameScoped.getCounter());
        Assert.assertEquals("i'm an ALTERNATIVE in GameContext", beanWithGameScoped.getString());
        Assert.assertEquals(1, beanWithGameScoped.getCounter());
        Assert.assertEquals("i'm an ALTERNATIVE in GameContext", beanWithGameScopedAlternative.getString());
        Assert.assertEquals(1, beanWithGameScopedAlternative.getCounter());
    }
}