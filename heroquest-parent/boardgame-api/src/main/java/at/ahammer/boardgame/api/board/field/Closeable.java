package at.ahammer.boardgame.api.board.field;

public interface Closeable {

    public boolean isLocked();

    public boolean isUnlocked();

    public boolean isClosed();

    public boolean isOpened();

    public void open();

    public void close();

}
