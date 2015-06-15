package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.subject.GameSubjectBasic;
import at.ahammer.boardgame.core.subject.GameSubjectNull;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
