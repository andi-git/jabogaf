package at.ahammer.boardgame.behavior.move;

/**
 * {@link at.ahammer.boardgame.object.field.Field}s are not connected.
 */
public class FieldsNotConnectedException extends Exception {

    public FieldsNotConnectedException(String message) {
        super(message);
    }
}
