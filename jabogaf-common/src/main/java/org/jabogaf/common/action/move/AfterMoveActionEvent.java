package org.jabogaf.common.action.move;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.action.AfterActionEventBasic;

/**
 * This event will be fired after a {@link MoveAction} and holds the {@link
 * MoveActionParameter}.
 */
public class AfterMoveActionEvent extends AfterActionEventBasic<MoveActionParameter> {

    public AfterMoveActionEvent(MoveActionParameter actionParameter) {
        super(actionParameter);
    }

    public GameSubject getGameSubject() {
        return getActionParameter().getGameSubject();
    }

    public Field getTarget() {
        return getActionParameter().getTarget();
    }
}
