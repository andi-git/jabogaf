package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.layout.log.LayoutLoggerParameter;
import org.jabogaf.api.subject.GameSubject;

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
