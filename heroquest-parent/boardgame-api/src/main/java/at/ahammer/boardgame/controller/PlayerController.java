package at.ahammer.boardgame.controller;

import at.ahammer.boardgame.subject.GameSubject;

/**
 * Manages the player, e.g. the current {@link at.ahammer.boardgame.subject.GameSubject}.
 * <p/>
 * This component must be {@link at.ahammer.boardgame.cdi.GameScoped}
 */
public interface PlayerController {

    /**
     * Check if the assigned {@link at.ahammer.boardgame.subject.GameSubject} is the current player.
     *
     * @param gameSubject the {@link at.ahammer.boardgame.subject.GameSubject} to check
     * @return {@code true} it the assigned {@link at.ahammer.boardgame.subject.GameSubject} is the current player
     */
    boolean isCurrentPlayer(GameSubject gameSubject);

    /**
     * Get the current player as {@link at.ahammer.boardgame.subject.GameSubject}
     * <p/>
     * Always use the getter because initialization in field will lead to an EmptyStackException (at bean-creation-time
     * context is not active)
     *
     * @return the current player as {@link at.ahammer.boardgame.subject.GameSubject}
     */
    GameSubject getCurrentPlayer();

    /**
     * Set the current player.
     *
     * @param currentPlayer the current player as {@link at.ahammer.boardgame.subject.GameSubject}
     */
    void setCurrentPlayer(GameSubject currentPlayer);
}
