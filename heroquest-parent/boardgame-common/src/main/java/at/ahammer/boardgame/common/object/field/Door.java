package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

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
