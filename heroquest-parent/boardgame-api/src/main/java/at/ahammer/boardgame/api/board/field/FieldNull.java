package at.ahammer.boardgame.api.board.field;

/**
 * The null-implementation of a {@link Field}.
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
