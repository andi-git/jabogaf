package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoveUnableToEndGameObjectOnField implements MoveUnableToEndForMoveBehaviorCommon {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean unableToEnd(Moveable moveable, Field target) {
        return !target.getGameObjects().isEmpty();
    }

    @Override
    public String toString() {
        return MoveUnableToEndGameObjectOnField.class.getSimpleName();
    }
}
