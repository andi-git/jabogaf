package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventGroup;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameStateChangedEventGroupNullTest {

    private GameStateChangedEventGroup gameStateChangedEventGroup;

    @Before
    public void before() {
        gameStateChangedEventGroup = new GameStateChangedEventGroupNull();
    }

    @Test
    public void testAddEvent() throws Exception {
        assertTrue(gameStateChangedEventGroup.getGameStateChangedEvents().isEmpty());
        gameStateChangedEventGroup.addEvent(new GameStateChangedEventValue(Instant.now(), null, new ArrayList<>()));
        assertTrue(gameStateChangedEventGroup.getGameStateChangedEvents().isEmpty());
    }

    @Test
    public void testGetGameStateChangedEvents() throws Exception {
        assertNotNull(gameStateChangedEventGroup.getGameStateChangedEvents());
        assertTrue(gameStateChangedEventGroup.getGameStateChangedEvents().isEmpty());
    }

    @Test
    public void testGetUUID() throws Exception {
        assertNotNull(gameStateChangedEventGroup.getUUID());
    }

    @Test
    public void testGetCreationTime() throws Exception {
        Thread.sleep(100);
        assertTrue(Instant.now().isAfter(gameStateChangedEventGroup.creationTime()));
    }
}