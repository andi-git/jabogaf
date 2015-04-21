package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.layout.log.LayoutLoggerParameter;
import at.ahammer.boardgame.api.subject.GameSubject;

public class GridLayoutLoggerParameter implements LayoutLoggerParameter {

    private GameSubject gameSubjectToShowPossibleMoves;

    public GridLayoutLoggerParameter() {
    }

    public GridLayoutLoggerParameter(GameSubject gameSubjectToShowPossibleMoves) {
        this.gameSubjectToShowPossibleMoves = gameSubjectToShowPossibleMoves;
    }

    public GameSubject getGameSubjectToShowPossibleMoves() {
        return gameSubjectToShowPossibleMoves;
    }

    public void setGameSubjectToShowPossibleMoves(GameSubject gameSubjectToShowPossibleMoves) {
        this.gameSubjectToShowPossibleMoves = gameSubjectToShowPossibleMoves;
    }
}
