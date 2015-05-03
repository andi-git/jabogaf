package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.layout.log.LayoutLoggerParameter;
import at.ahammer.boardgame.api.subject.GameSubject;

import java.util.Optional;

public class GridLayoutLoggerParameter implements LayoutLoggerParameter {

    private Optional<GameSubject> gameSubjectOfInterest = Optional.empty();

    public GridLayoutLoggerParameter() {}

    public GridLayoutLoggerParameter(GameSubject gameSubjectOfInterest) {
        this.gameSubjectOfInterest = Optional.of(gameSubjectOfInterest);
    }

    public Optional<GameSubject> getGameSubjectOfInterest() {
        return gameSubjectOfInterest;
    }
}
