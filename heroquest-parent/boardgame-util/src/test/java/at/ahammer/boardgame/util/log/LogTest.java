package at.ahammer.boardgame.util.log;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class LogTest {

    @Inject
    @SLF4J
    private Logger log;

    @Test
    public void testLog() {
        Assert.assertNotNull(log);
    }
}
