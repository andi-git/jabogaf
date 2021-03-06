package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.AlternativeInGameContext;
import org.jabogaf.core.gamecontext.bean.BeanWithGameScoped;
import org.jabogaf.core.gamecontext.bean.BeanWithGameScopedAlternative;
import org.jabogaf.test.gamecontext.ActivateAlternatives;
import org.jabogaf.test.gamecontext.ArquillianGameContext;
import org.jabogaf.test.gamecontext.ArquillianGameContextTest;
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