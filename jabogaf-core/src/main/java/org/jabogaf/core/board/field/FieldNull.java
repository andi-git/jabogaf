package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;

/**
 * The null-implementation of a {@link org.jabogaf.api.board.field.Field}.
 */
public class FieldNull extends FieldBasic {

    public FieldNull() {
        super(randomId());
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
