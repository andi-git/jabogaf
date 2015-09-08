package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.behavior.move.MovePath;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * A line with the id of a concrete {@link GameContextBean}.
 */
@ApplicationScoped
public class FieldLineMoveableFields extends FieldLine<FieldLineMoveableFields.Representation> {

    @Override
    public int rank() {
        return 60;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        boolean isLast = true;
        if (!isAMovePointAlreadyExisting(state) && state.getParameter().getGameSubjectOfInterest() != null) {
            isLast = isPreLastElement(state) || !getMovePath(state).isPresent();
        }
        return isLast;
    }

    @Override
    public FieldLineMoveableFields.Representation create(FactoryForFieldLines.State state) {
        FieldLineMoveableFields.Representation representation = null;
        Optional<GameSubject> gameSubject = state.getParameter().getGameSubjectOfInterest();
        Optional<MovePath> movePath = getMovePath(state);
        if (gameSubject.isPresent() && movePath.isPresent()) {
            representation = new FieldLineMoveableFields.Representation(gameSubject.get(), movePath.get().cost().getAmount(), state.getStringUtil());
        }
        return representation;
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }

    private Optional<MovePath> getMovePath(FactoryForFieldLines.State state) {
        Optional<MovePath> movePath = Optional.empty();
        if (state.getParameter().getGameSubjectOfInterest().isPresent()) {
            movePath = state.getParameter().getGameSubjectOfInterest().get().
                    getMovableFields().
                    stream().
                    filter(mp -> mp.getTarget().equals(state.getField())).
                    findFirst();
        }
        return movePath;
    }

    private boolean isAMovePointAlreadyExisting(FactoryForFieldLines.State state) {
        return state.getFieldLineRepresentations().stream().anyMatch(f -> f instanceof FieldLineMoveableFields.Representation);
    }

    public static class Representation extends FieldLine.Representation {

        private final GameSubject gameSubject;

        private final int movePoints;

        public Representation(GameSubject gameSubject, int movePoints, StringUtil stringUtil) {
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
    }
}
