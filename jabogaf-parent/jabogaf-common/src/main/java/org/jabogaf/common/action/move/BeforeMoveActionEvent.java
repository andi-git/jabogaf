package org.jabogaf.common.action.move;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.action.BeforeActionEventBasic;

/**
 * This event will be fired before a {@link MoveAction} and holds the {@link MoveActionParameter}.
 */
public class BeforeMoveActionEvent extends BeforeActionEventBasic<MoveActionParameter> {

    public BeforeMoveActionEvent(MoveActionParameter actionParameter) {
        super(actionParameter);
    }

    public GameSubject getGameSubject() {
        return getActionParameter().getGameSubject();
    }

    public Field getTarget() {
        return getActionParameter().getTarget();
    }
}
