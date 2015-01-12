package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.loader.ServiceLoader;
import at.ahammer.boardgame.api.subject.GameSubject;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * The basic implementation of {@link at.ahammer.boardgame.api.controller.PlayerController}.
 */
@GameScoped
public class PlayerControllerBasic implements PlayerController {

    @Inject
    private GameContextManager gameContextManager;

    private GameSubject currentPlayer;

    @Override
    public boolean isCurrentPlayer(GameSubject gameSubject) {
        return getCurrentPlayer().equals(gameSubject);
    }

    @Override
    public GameSubject getCurrentPlayer() {
        if (currentPlayer == null) {
            currentPlayer = ServiceLoader.get(GameSubject.class);
        }
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(GameSubject currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public Set<GameSubject> getAllGameSubjects() {
        return gameContextManager.getGameContextBeans(GameSubject.class);
    }

    @Override
    public Set<GameSubject> getAllGameSubjects(Field field) {
        Set<GameSubject> gameSubjects = new HashSet<>();
        for (GameSubject gameSubject : getAllGameSubjects()) {
            if (field.equals(gameSubject.getPosition())) {
                gameSubjects.add(gameSubject);
            }
        }
        return gameSubjects;
    }
}
