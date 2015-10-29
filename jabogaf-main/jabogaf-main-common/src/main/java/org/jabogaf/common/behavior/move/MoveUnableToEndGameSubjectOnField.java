package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.Wall;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoveUnableToEndGameSubjectOnField implements MoveUnableToEndForMoveBehaviorCommon {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean unableToEnd(Moveable moveable, Field target) {
        return !target.getGameSubjects().isEmpty();
    }

    @Override
    public String toString() {
        return MoveUnableToEndGameSubjectOnField.class.getSimpleName();
    }
}
