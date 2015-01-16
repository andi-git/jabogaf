package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.field.Closeable;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

public class Door extends FieldConnectionObject implements Closeable {

    private boolean locked = true;

    private boolean closed = true;

    public Door(String id) {
        super(id);
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isUnlocked() {
        return !locked;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isOpened() {
        return !closed;
    }

    @Override
    public void open() {
        closed = false;
    }

    @Override
    public void close() {
        closed = true;
    }
}
