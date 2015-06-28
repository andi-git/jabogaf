package org.jabogaf.common.object.field;

import org.jabogaf.api.board.field.Closeable;
import org.jabogaf.core.board.field.FieldConnectionObjectBasic;

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
