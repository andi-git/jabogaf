package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.jabogaf.util.log.SLF4J;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

@RunWith(ArquillianGameContext.class)
public class CachedValueTest extends ArquillianGameContextTest {

    @Inject
    private CachedValueMock cachedValueMock;

    @Test
    public void testGetCachedWhereCallableThrowsException() throws Exception {
        cachedValueMock.getCached();
        assertFalse(cachedValueMock.isValid());
    }

    @GameScoped
    public static class CachedValueMock extends CachedValue<String> {

        @Inject
        @SLF4J
        private Logger log;

        protected String getCached() {
            return getCached(() -> {
                throw new RuntimeException("exception from mock");
            }, false);
        }

        @Override
        protected Logger log() {
            return log;
        }
    }
}