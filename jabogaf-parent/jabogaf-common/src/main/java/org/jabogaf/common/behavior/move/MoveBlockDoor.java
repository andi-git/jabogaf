package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.Door;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoveBlockDoor implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        boolean isBlocked = false;
        FieldConnection fieldConnection = boardManager.getBoard().getLayout().getConnection(moveable.getPosition(), target);
        if (fieldConnection != null && !fieldConnection.getObjectsOnConnection().isEmpty()) {
            isBlocked = fieldConnection.getObjectsOnConnection().stream().
                    filter(o -> o instanceof Door).
                    map(o -> (Door) o).
                    anyMatch(Door::isClosed);
        }
        return isBlocked;
    }

    @Override
    public String toString() {
        return Door.class.getSimpleName();
    }
}
