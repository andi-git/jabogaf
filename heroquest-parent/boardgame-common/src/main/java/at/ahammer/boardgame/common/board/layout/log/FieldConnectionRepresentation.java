package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Closeable;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.field.Hidden;

public abstract class FieldConnectionRepresentation implements GridLayoutLoggerElement {

    private final FieldConnection fieldConnection;

    protected FieldConnectionRepresentation(FieldConnection fieldConnection) {
        this.fieldConnection = fieldConnection;
    }

    protected FieldConnectionObject getFirstFieldConnectionObject() {
        if (fieldConnection == null || fieldConnection.getObjectsOnConnection().isEmpty()) {
            return null;
        }
        return fieldConnection.getObjectsOnConnection().stream().findFirst().get();
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
            if (!((Hidden) getFirstFieldConnectionObject()).isVisible()) {
                return '_';
            } else {
                return '#';
            }
        } else {
            return ' ';
        }
    }
}
