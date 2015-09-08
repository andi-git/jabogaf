package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEventGroup;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameStateChangedEventGroupBasicTest {

    private GameStateChangedEventGroup gameStateChangedEventGroup;

    @Before
    public void before() {
        gameStateChangedEventGroup = new GameStateChangedEventGroupBasic();
    }

    @Test
    public void testAddEvent() throws Exception {
        gameStateChangedEventGroup.addEvent(new GameStateChangedEventValue(Instant.now(), null, new ArrayList<>()));
        assertEquals(1, gameStateChangedEventGroup.getGameStateChangedEvents().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGameStateChangedEvents() throws Exception {
        assertTrue(gameStateChangedEventGroup.getGameStateChangedEvents().isEmpty());
        gameStateChangedEventGroup.getGameStateChangedEvents().remove(0);
    }

    @Test
    public void testGetUUID() throws Exception {
        assertNotNull(gameStateChangedEventGroup.getUUID());
    }

    @Test
    public void testEqualsAndHashcode() {
        GameStateChangedEventGroup gameStateChangedEventGroup1 = new GameStateChangedEventGroupBasic();
        GameStateChangedEventGroup gameStateChangedEventGroup2 = new GameStateChangedEventGroupBasic();
        assertFalse(gameStateChangedEventGroup1.equals(gameStateChangedEventGroup2));
        assertFalse(gameStateChangedEventGroup2.equals(gameStateChangedEventGroup1));
        assertTrue(gameStateChangedEventGroup1.equals(gameStateChangedEventGroup1));
        assertNotEquals(gameStateChangedEventGroup1.hashCode(), gameStateChangedEventGroup2.hashCode());
    }
}