package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.event.BeforeActionEvent;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * This event will be fired before a {@link MoveAction} and holds the {@link MoveActionParameter}.
 */
public class BeforeMoveActionEvent extends BeforeActionEvent<MoveActionParameter> {

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
