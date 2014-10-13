package at.ahammer.boardgame.controller;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.subject.GameSubject;
import at.ahammer.boardgame.subject.GameSubjectNull;

/**
 * Created by andreas on 08.10.14.
 */
@GameScoped
public class PlayerController {

    private GameSubject currentPlayer;

    public boolean isCurrentPlayer(GameSubject gameSubject) {
        return getCurrentPlayer().equals(gameSubject);
    }

    /**
     * Always use the getter because initialization in field will lead to an EmptyStackException (at bean-creation-time context is not active)
     *
     * @return
     */
    public GameSubject getCurrentPlayer() {
        if (currentPlayer == null) {
            currentPlayer = new GameSubjectNull();
        }
        return currentPlayer;
    }

    public void setCurrentPlayer(GameSubject currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
