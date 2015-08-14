package org.jabogaf.core.event;

import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.event.GameStateChangedCollector;
import org.jabogaf.api.state.GameState;
import org.jabogaf.util.copy.CloneBeanBasic;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class GameStateChangedCollectorTest {

    @Inject
    private GameStateChangedCollector gameStateChangedCollector;

    @Inject
    private Event<GameStateChanged> gameStateChangedEvent;

    @Inject
    private Bean bean;

    @Test
    public void testGameStateChangedCollector() throws Exception {
        assertTrue(gameStateChangedCollector.getGameStateChanges().isEmpty());
        gameStateChangedEvent.fire(new GameStateChangedValue(Instant.now(),
                getClass().getDeclaredMethod("testGameStateChangedCollector"), Arrays.asList("foo")));
        gameStateChangedEvent.fire(new GameStateChangedValue(Instant.now(),
                getClass().getDeclaredMethod("testGameStateChangedCollector"), Arrays.asList("foo")));
        gameStateChangedEvent.fire(new GameStateChangedBean(Instant.now(), bean));
        assertEquals(3, gameStateChangedCollector.getGameStateChanges().size());

    }

    public static class Bean implements GameContextBean {

        @Inject
        private State state;

        @Override
        public String getId() {
            return "";
        }

        @Override
        public GameState getState() {
            return state;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        public static class State extends GameState<Bean> {

            @Override
            public Class<Bean> classOfContainingBean() {
                return Bean.class;
            }
        }
    }
}