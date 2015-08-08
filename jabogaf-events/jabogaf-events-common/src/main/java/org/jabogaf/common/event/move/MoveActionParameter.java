package org.jabogaf.common.event.move;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.GameSubject;

/**
 * Parameters for a {@code MoveAction}.
 *
 * FIXME: move to another module
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
