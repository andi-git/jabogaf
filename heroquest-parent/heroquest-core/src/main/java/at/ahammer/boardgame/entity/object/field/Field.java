package at.ahammer.boardgame.entity.object.field;

import at.ahammer.boardgame.entity.board.Board;
import at.ahammer.boardgame.entity.object.GameObject;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class Field extends GameObject {

    protected Field(String id) {
        super(id);
    }

    public boolean isConnected(Field other) {
        return fromGameContext(Board.class).isConnected(this, other);
    }

    public FieldConnection getConnectionTo(Field other) {
        return fromGameContext(Board.class).getConnection(this, other);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
