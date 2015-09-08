package org.jabogaf.api.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * This component groups some events, which belongs together.
 */
public interface GameStateChangedEventGroup {

    /**
     * Add a {@link GameStateChangedEvent} to the {@link GameStateChangedEventGroup}.
     *
     * @param gameStateChangedEvent - the {@link GameStateChangedEvent} to add
     */
    void addEvent(GameStateChangedEvent gameStateChangedEvent);

    /**
     * Get a {@link List} off all {@link GameStateChangedEvent}s that belong to this {@link
     * GameStateChangedEventGroup}.
     *
     * @return a {@link List} off all {@link GameStateChangedEvent}s that belong to this {@link
     * GameStateChangedEventGroup}
     */
    List<GameStateChangedEvent> getGameStateChangedEvents();

    /**
     * Get the {@link UUID} of this {@link GameStateChangedEventGroup}.
     *
     * @return the {@link UUID} of this {@link GameStateChangedEventGroup}
     */
    UUID getUUID();

    /**
     * Get the creation-time.
     *
     * @return the creation time
     */
    Instant creationTime();
}
