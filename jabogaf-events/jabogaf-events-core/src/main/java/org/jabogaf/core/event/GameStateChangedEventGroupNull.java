package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventGroup;

import javax.enterprise.inject.Typed;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * The null-implementation of {@link GameStateChangedEventGroup} that can be used if no {@link
 * GameStateChangedEventGroup} is active.
 */
@Typed
public class GameStateChangedEventGroupNull implements GameStateChangedEventGroup {

    private final UUID uuid = UUID.randomUUID();

    private final Instant creationTime = Instant.now();

    @Override
    public void addEvent(GameStateChangedEvent gameStateChangedEvent) {
        // nothing
    }

    @Override
    public List<GameStateChangedEvent> getGameStateChangedEvents() {
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Instant creationTime() {
        return creationTime;
    }
}
