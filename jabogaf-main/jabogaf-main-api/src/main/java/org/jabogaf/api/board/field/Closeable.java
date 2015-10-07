package org.jabogaf.api.board.field;

public interface Closeable {

    boolean isLocked();

    boolean isUnlocked();

    boolean isClosed();

    boolean isOpened();

    void open();

    void close();
}
