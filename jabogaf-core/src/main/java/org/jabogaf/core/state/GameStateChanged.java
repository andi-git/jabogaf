package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameContextBean;

import java.lang.reflect.Method;

/**
 * The cdi-event when the game state changed. For example it is called if a new {@link GameContextBean} is created or a value of an instance of {@link GameState} is changed.
 */
public class GameStateChanged {

    private final Method method;

    public GameStateChanged() {
        this(null);
    }

    public GameStateChanged(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
