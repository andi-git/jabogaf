package org.jabogaf.core.controller;

import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.subject.GameSubjectNull;

/**
 * The basic implementation of {@link org.jabogaf.api.controller.PlayerController}.
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
