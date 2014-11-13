package at.ahammer.boardgame.cdi;

import at.ahammer.boardgame.test.ActivateAlternatives;
import at.ahammer.boardgame.test.ArquillianGameContext;
import at.ahammer.boardgame.test.ArquillianGameContextTest;
import at.ahammer.boardgame.test.BeforeInGameContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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