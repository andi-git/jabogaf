package org.jabogaf.api.event;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The cdi-event when the game state changed. For example it is called if a new {@code GameContextBean} is created or a
 * value of an instance of {@code GameState} is changed.
 */
public abstract class GameStateChangedEvent {

    private final Instant time;

    protected GameStateChangedEvent(Instant time) {
        this.time = time != null ? time : Instant.now();
    }

    public Instant getTime() {
        return time;
    }
}
