package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.event.BeforeActionEvent;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.action.BeforeActionEventBasic;

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
