package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventCollector;
import org.jabogaf.api.event.GameStateChangedEventGroupHandler;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.state.GameState;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GameStateChangedEventGroupHandlerBasicTest {

    @Inject
    private GameStateChangedEventGroupHandler gameStateChangedEventGroupHandler;

    @Inject
    private Event<GameStateChangedEvent> gameStateChangedEvent;

    @Inject
    private GameStateChangedEventCollector gameStateChangedEventCollector;

    @Inject
    private Bean bean;

    @Test
    public void testGameStateChangedEventGroupHandler() throws Exception {
        assertTrue(gameStateChangedEventCollector.getGameStateChangedEventGroups().isEmpty());
        assertTrue(gameStateChangedEventGroupHandler.getCurrentGameStateChangedEventGroup().getGameStateChangedEvents().isEmpty());

        gameStateChangedEventGroupHandler.withinOwnEventGroup(() -> {
            try {
                gameStateChangedEvent.fire(new GameStateChangedEventValue(Instant.now(),
                        getClass().getDeclaredMethod("testGameStateChangedEventGroupHandler"), Collections.singletonList("foo")));
                gameStateChangedEvent.fire(new GameStateChangedEventValue(Instant.now(),
                        getClass().getDeclaredMethod("testGameStateChangedEventGroupHandler"), Collections.singletonList("foo")));
                gameStateChangedEvent.fire(new GameStateChangedEventBean(Instant.now(), bean));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        assertFalse(gameStateChangedEventCollector.getGameStateChangedEventGroups().isEmpty());
        assertEquals(1, gameStateChangedEventCollector.getGameStateChangedEventGroups().size());
        assertEquals(3, gameStateChangedEventCollector.getGameStateChangedEventGroupsOrderedByTime().get(0).getGameStateChangedEvents().size());

        gameStateChangedEventGroupHandler.withinOwnEventGroup(() -> {
            gameStateChangedEvent.fire(new GameStateChangedEventBean(Instant.now(), bean));
        });
        assertFalse(gameStateChangedEventCollector.getGameStateChangedEventGroups().isEmpty());
        assertEquals(2, gameStateChangedEventCollector.getGameStateChangedEventGroups().size());
        assertEquals(3,
                gameStateChangedEventCollector.getGameStateChangedEventGroupsOrderedByTime().get(0).getGameStateChangedEvents().size());
        assertEquals(1,
                gameStateChangedEventCollector.getGameStateChangedEventGroupsOrderedByTime().get(1).getGameStateChangedEvents().size());
    }

    @Dependent
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

        @SuppressWarnings("NullableProblems")
        @Override
        public int compareTo(Object o) {
            return 0;
        }

        @Dependent
        public static class State extends GameState<Bean> {

            @Override
            public Class<Bean> classOfContainingBean() {
                return Bean.class;
            }
        }
    }

}