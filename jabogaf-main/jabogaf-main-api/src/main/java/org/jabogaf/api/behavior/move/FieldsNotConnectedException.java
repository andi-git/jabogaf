package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;

/**
 * {@link Field}s are not connected.
 */
public class FieldsNotConnectedException extends RuntimeException {

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
