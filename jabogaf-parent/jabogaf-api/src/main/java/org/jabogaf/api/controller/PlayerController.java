package org.jabogaf.api.controller;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.subject.GameSubject;

/**
 * Manages the player, e.g. the current {@link GameSubject}.
 * <p>
 * This component must be {@link GameScoped}
 */
public interface PlayerController {

    /**
     * Check if the assigned {@link GameSubject} is the current player.
     *
     * @param gameSubject the {@link GameSubject} to check
     * @return {@code true} it the assigned {@link GameSubject} is the current player
     */
    boolean isCurrentPlayer(GameSubject gameSubject);

    /**
     * Get the current player as {@link GameSubject}
     * <p>
     * Always use the getter because initialization in field will lead to an EmptyStackException (at bean-creation-time
     * context is not active)
     *
     * @return the current player as {@link GameSubject}
     */
    GameSubject getCurrentPlayer();

    /**
     * Set the current player.
     *
     * @param currentPlayer the current player as {@link GameSubject}
     */
    void setCurrentPlayer(GameSubject currentPlayer);
}
