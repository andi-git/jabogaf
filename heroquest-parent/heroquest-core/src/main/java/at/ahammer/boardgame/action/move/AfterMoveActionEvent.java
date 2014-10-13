package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.AfterActionEvent;
import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * Created by andreas on 08.10.14.
 */
public class AfterMoveActionEvent extends AfterActionEvent<MoveAction> {

    private GameSubject gameSubject;

    private Field target;

    public AfterMoveActionEvent(GameSubject gameSubject, Field target) {
        this.gameSubject = gameSubject;
        this.target = target;
    }

    public GameSubject getGameSubject() {
        return gameSubject;
    }

    public Field getTarget() {
        return target;
    }
}
