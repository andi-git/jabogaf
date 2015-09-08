package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The game-state-event when a simple value changed.
 */
public class GameStateChangedEventValue extends GameStateChangedEvent {

    private final Method method;

    private final List<Object> values = new ArrayList<>();

    public GameStateChangedEventValue(Instant time, Method method, List<Object> values) {
        super(time);
        this.method = method;
        this.values.addAll(values);
    }

    public GameStateChangedEventValue(GameStateChangedEventValue gameStateChangeValue) {
        super(gameStateChangeValue.getTime());
        this.method = gameStateChangeValue.getMethod();
        this.values.addAll(gameStateChangeValue.getValues());
    }

    public Method getMethod() {
        return method;
    }

    public List<Object> getValues() {
        return Collections.unmodifiableList(values);
    }
}
