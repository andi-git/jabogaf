package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.board.field.Closeable;
import at.ahammer.boardgame.core.board.field.FieldConnectionObjectBasic;

public class Door extends FieldConnectionObjectBasic implements Closeable {

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
