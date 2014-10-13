package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.move.MoveBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public class Door extends FieldConnectionObject {

    private boolean locked = true;

    private boolean closed = true;

    public Door(String id) {
        super(id);
    }

    @Override
    public boolean canMove(MoveBehavior moveBehavior) {
        return false;
    }

    @Override
    public boolean canLook(LookBehavior lookBehavior) {
        return false;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isClosed() {
        return closed;
    }

    public void open() {
        closed = false;
    }

    public void close() {
        closed = true;
    }
}
