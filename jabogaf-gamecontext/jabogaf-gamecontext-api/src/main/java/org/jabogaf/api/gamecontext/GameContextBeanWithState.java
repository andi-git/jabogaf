package org.jabogaf.api.gamecontext;

import org.jabogaf.api.state.GameState;

/**
 * This is a {@link GameContextBean} with a {@link GameState}
 */
public interface GameContextBeanWithState<T extends GameContextBeanWithState> extends GameContextBean<T> {

    /**
     * Get the {@link GameState} of the current {@link GameContextBean}.
     *
     * @return the {@link GameState} of the current {@link GameContextBean}
     */
    GameState<T> getState();
}
