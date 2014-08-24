package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.board.Board;
import at.ahammer.boardgame.object.GameObject;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class Field extends GameObject {

    protected Field(String id) {
        super(id);
    }

    public boolean isConnected(Field other) {
        return fromGameContext(Board.class).getLayout().isConnected(this, other);
    }

    public FieldConnection getConnectionTo(Field other) {
        return fromGameContext(Board.class).getLayout().getConnection(this, other);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
