package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.event.AfterActionEvent;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.action.AfterActionEventBasic;

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
