package at.ahammer.boardgame.api.behavior.move;

/**
 * {@link at.ahammer.boardgame.api.board.field.Field}s are not connected.
 */
public class FieldsNotConnectedException extends Exception {

    public FieldsNotConnectedException(String message) {
        super(message);
    }
}
