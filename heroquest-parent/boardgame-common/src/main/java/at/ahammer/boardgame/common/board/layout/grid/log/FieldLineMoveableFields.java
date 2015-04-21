package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.behavior.move.MoveableFields;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * A line with the id of a concrete {@link GameContextBean}.
 */
public class FieldLineMoveableFields extends FieldLine {

    private final GameSubject gameSubject;

    private final int movePoints;

    public FieldLineMoveableFields(GameSubject gameSubject, int movePoints, StringUtil stringUtil) {
        super(stringUtil);
        this.gameSubject = gameSubject;
        this.movePoints = movePoints;
    }

    @Override
    public String text() {
        return ">mp-" + gameSubject.getId() + ":" + movePoints;
    }

    @Override
    public char firstChar() {
        return '|';
    }

    @Override
    public char lastChar() {
        return '|';
    }

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineMoveableFields> {

        @Inject
        private MoveableFields moveableFields;

        @Override
        public int rank() {
            return 60;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            boolean isLast = true;
            if (!isAMovePointAlreadyExisting(state) && state.getParameter().getGameSubjectToShowPossibleMoves() != null) {
                isLast = isPreLastElement(state) || !getMovePath(state).isPresent();
            }
            return isLast;
        }

        @Override
        public FieldLineMoveableFields create(FactoryForFieldLines.State state) {
            return new FieldLineMoveableFields(state.getParameter().getGameSubjectToShowPossibleMoves(), getMovePath(state).get().cost().getAmount(), state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return false;
        }

        private Optional<MovePath> getMovePath(FactoryForFieldLines.State state) {
            List<MovePath> movePaths = moveableFields.get(new MoveableFields.Parameter(state.getParameter().getGameSubjectToShowPossibleMoves()));
            return movePaths.stream().filter(mp -> mp.getTarget().equals(state.getField())).findFirst();
        }

        private boolean isAMovePointAlreadyExisting(FactoryForFieldLines.State state) {
            return state.getFieldLines().stream().anyMatch(f -> f instanceof FieldLineMoveableFields);
        }
    }
}
