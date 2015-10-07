package org.jabogaf.api.gamecontext;

import org.jabogaf.api.state.GameState;

/**
 * This is the superclass for all new instantiated beans game-context which have a state (belongs to tha game-state).
 * This class only provides the method {@link #getId()} as abstract method and it's not an obligation to use this class
 * as superclass.
 */
public interface GameContextBean<T extends GameContextBean> extends Comparable<GameContextBean> {

    /**
     * Get the id of the {@link GameContextBean} in the game-context.
     *
     * @return the id of the {@link GameContextBean} in the game-context
     */
    String getId();
}
