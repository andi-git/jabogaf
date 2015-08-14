package org.jabogaf.core.state;

import org.jabogaf.api.state.GameState;

/**
 * The null-implementation of the {@link GameState}.
 */
public class GameStateNull extends GameState<Object> {

    @Override
    public Class<Object> classOfContainingBean() {
        return Object.class;
    }

    @Override
    public void deserialize(String string) {

    }

    @Override
    public String serialize() {
        return "";
    }
}
