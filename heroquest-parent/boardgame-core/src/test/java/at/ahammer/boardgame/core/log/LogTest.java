package at.ahammer.boardgame.core.log;

import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

@RunWith(ArquillianGameContext.class)
public class LogTest extends ArquillianGameContextTest {

    @Inject
    private Logger log;

    @Test
    public void testLog() {
        Assert.assertNotNull(log);
    }
}
