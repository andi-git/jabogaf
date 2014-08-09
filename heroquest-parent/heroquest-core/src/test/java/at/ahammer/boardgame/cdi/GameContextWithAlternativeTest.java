package at.ahammer.boardgame.cdi;

import at.ahammer.boardgame.test.util.ActivateAlternatives;
import at.ahammer.boardgame.test.util.ArquillianGameContext;
import at.ahammer.boardgame.test.util.ArquillianGameContextTest;
import at.ahammer.boardgame.test.util.RunAllMethodsInGameContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by andreas on 03.08.14.
 */
@RunWith(ArquillianGameContext.class)
@ActivateAlternatives({BeanWithGameScopedAlternative.class})
public class GameContextWithAlternativeTest extends ArquillianGameContextTest implements RunAllMethodsInGameContext {

    @Inject
    private BeanWithGameScoped beanWithGameScoped;

    @Test
    public void testComponentInGameContextAlternative() throws Throwable {
            Assert.assertNotNull(beanWithGameScoped.getString());
            Assert.assertEquals("alternative", beanWithGameScoped.getString());
    }

}