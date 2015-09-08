package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventGroup;

import javax.enterprise.inject.Typed;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Typed
public class GameStateChangedEventGroupBasic implements GameStateChangedEventGroup {

    private final List<GameStateChangedEvent> gameStateChangedEvents = new ArrayList<>();

    private final UUID uuid = UUID.randomUUID();

    private final Instant creationTime = Instant.now();

    @Override
    public void addEvent(GameStateChangedEvent gameStateChangedEvent) {
        gameStateChangedEvents.add(gameStateChangedEvent);
    }

    @Override
    public List<GameStateChangedEvent> getGameStateChangedEvents() {
        return Collections.unmodifiableList(gameStateChangedEvents);
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStateChangedEventGroupBasic that = (GameStateChangedEventGroupBasic) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public Instant creationTime() {
        return creationTime;
    }
}
