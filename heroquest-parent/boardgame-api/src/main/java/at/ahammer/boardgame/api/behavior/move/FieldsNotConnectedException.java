package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;

/**
 * {@link Field}s are not connected.
 */
public class FieldsNotConnectedException extends Exception {

    private final Field position;

    private final Field target;

    public FieldsNotConnectedException(Field position, Field target) {
        super("fields " + position + " and " + target + " are not connected");
        this.position = position;
        this.target = target;
    }

    public Field getPosition() {
        return position;
    }

    public Field getTarget() {
        return target;
    }
}
