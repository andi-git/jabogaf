package at.ahammer.boardgame.action.move;

import at.ahammer.boardgame.action.GameActionParameter;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * Parameters for a {@link at.ahammer.boardgame.action.move.MoveAction}.
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
