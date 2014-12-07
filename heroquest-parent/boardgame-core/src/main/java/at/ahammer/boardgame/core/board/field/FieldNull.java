package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

/**
 * The null-implementation of a {@link at.ahammer.boardgame.api.board.field.Field}.
 */
public class FieldNull extends Field {

    public FieldNull() {
        super(String.valueOf(System.nanoTime()));
    }

    @Override
    public boolean isConnected(Field target) {
        return false;
    }

    @Override
    public FieldConnection getConnectionTo(Field target) {
        return new FieldConnectionNull();
    }
}
