package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.GameSubjectNull;

/**
 * The basic implementation of {@link at.ahammer.boardgame.api.controller.PlayerController}.
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
