package at.ahammer.boardgame.controller;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.subject.GameSubject;
import at.ahammer.boardgame.subject.GameSubjectNull;

/**
 * The basic implementation of {@link at.ahammer.boardgame.controller.PlayerController}.
 */
@GameScoped
public class PlayerControllerBasic implements PlayerController {

    private GameSubject currentPlayer;

    @Override
    public boolean isCurrentPlayer(GameSubject gameSubject) {
        return getCurrentPlayer().equals(gameSubject);
    }

    @Override
    public GameSubject getCurrentPlayer() {
        if (currentPlayer == null) {
            currentPlayer = new GameSubjectNull();
        }
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(GameSubject currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
