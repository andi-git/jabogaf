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
public class GameStateChanged {

    private final Instant time;

    private final Method method;

    private final List<Object> values = new ArrayList<>();

    public GameStateChanged(Instant time, Method method, List<Object> values) {
        this.time = time;
        this.method = method;
        this.values.addAll(values);
    }

    public GameStateChanged(GameStateChanged gameStateChange) {
        if (gameStateChange == null) {
            this.time = Instant.now();
            this.method = null;
        } else {
            this.time = gameStateChange.getTime();
            this.method = gameStateChange.getMethod();
            this.values.addAll(gameStateChange.getValues());
        }
    }

    public Instant getTime() {
        return time;
    }

    public Method getMethod() {
        return method;
    }

    public List<Object> getValues() {
        return Collections.unmodifiableList(values);
    }
}
