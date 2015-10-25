package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.Closeable;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.Hidden;
import org.jabogaf.api.object.GameObject;

public abstract class FieldConnectionRepresentation implements GridLayoutLoggerElement {

    private final FieldConnection fieldConnection;

    protected FieldConnectionRepresentation(FieldConnection fieldConnection) {
        this.fieldConnection = fieldConnection;
    }

    protected GameObject getFirstFieldConnectionObject() {
        if (fieldConnection == null || fieldConnection.getGameObjects().isEmpty()) {
            return null;
        }
        return fieldConnection.getGameObjects().stream().findFirst().get();
    }

    protected char openCloseChar() {
        if (getFirstFieldConnectionObject() instanceof Closeable) {
            if (((Closeable) getFirstFieldConnectionObject()).isClosed()) {
                return '~';
            } else {
                return '/';
            }
        } else {
            return ' ';
        }
    }

    protected char hiddenChar() {
        if (getFirstFieldConnectionObject() instanceof Hidden) {
            if (!getFirstFieldConnectionObject().isVisible()) {
                return '_';
            } else {
                return '#';
            }
        } else {
            return ' ';
        }
    }
}
