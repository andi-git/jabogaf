package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.event.AfterActionEvent;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * This event will be fired after a {@link at.ahammer.boardgame.action.move.MoveAction} and holds the {@link
 * at.ahammer.boardgame.action.move.MoveActionParameter}.
 */
public class AfterMoveActionEvent extends AfterActionEvent<MoveActionParameter> {

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
