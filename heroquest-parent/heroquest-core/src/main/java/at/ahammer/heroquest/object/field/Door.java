package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.subject.look.Look;
import at.ahammer.boardgame.subject.move.Move;

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
    public boolean canMove(Move move) {
        return false;
    }

    @Override
    public boolean canLook(Look look) {
        return false;
    }

    @Override
    public boolean isVisible() {
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
