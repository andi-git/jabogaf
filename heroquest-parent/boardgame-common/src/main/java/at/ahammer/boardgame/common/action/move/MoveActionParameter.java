package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.action.GameActionParameter;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.GameSubject;

/**
 * Parameters for a {@link MoveAction}.
 */
public class MoveActionParameter implements GameActionParameter {

    private final GameSubject gameSubject;

    private final Field target;

    public MoveActionParameter(GameSubject gameSubject, Field target) {
        this.gameSubject = gameSubject;
        this.target = target;
    }

    public GameSubject getGameSubject() {
        return gameSubject;
    }

    public Moveable getMoveable() {
        return gameSubject;
    }

    public ResourceHolder getResourceHolder() {
        return gameSubject;
    }

    public Field getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "MoveActionParameter{" +
                "gameSubject=" + gameSubject +
                ", target=" + target +
                '}';
    }
}
