package at.ahammer.boardgame.core.util.cache;

import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.core.state.GameStateChanged;
import at.ahammer.boardgame.core.test.ArquillianGameContext;
import at.ahammer.boardgame.core.test.ArquillianGameContextTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
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
        gameStateChangedEvent.fire(new GameStateChanged());
        assertEquals(1, cachedValueNoInputParam1.get().intValue());
    }

    @GameScoped
    public static class CachedValueNoInputParam1 extends CachedValueNoInputParam<Integer> {

        @Inject
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