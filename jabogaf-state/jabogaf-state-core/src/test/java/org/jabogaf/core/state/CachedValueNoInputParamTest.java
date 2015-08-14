package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.core.event.GameStateChangedValue;
import org.jabogaf.test.cdi.ArquillianGameContext;
import org.jabogaf.test.cdi.ArquillianGameContextTest;
import org.jabogaf.util.log.SLF4J;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(ArquillianGameContext.class)
public class CachedValueNoInputParamTest extends ArquillianGameContextTest {

    @Inject
    private CachedValueNoInputParam1 cachedValueNoInputParam1;

    @Inject
    private Event<GameStateChanged> gameStateChangedEvent;

    @Test
    public void testGet() throws Exception {
        assertEquals(0, cachedValueNoInputParam1.get().intValue());
        assertEquals(0, cachedValueNoInputParam1.get().intValue());
        gameStateChangedEvent.fire(new GameStateChangedValue(Instant.now(), null, new ArrayList<>()));
        assertEquals(1, cachedValueNoInputParam1.get().intValue());
    }

    @GameScoped
    public static class CachedValueNoInputParam1 extends CachedValueNoInputParam<Integer> {

        @Inject
        @SLF4J
        private Logger log;

        private int count;

        @Override
        protected Supplier<Integer> create() {
            return () -> count++;
        }

        @Override
        protected Logger log() {
            return log;
        }
    }
}