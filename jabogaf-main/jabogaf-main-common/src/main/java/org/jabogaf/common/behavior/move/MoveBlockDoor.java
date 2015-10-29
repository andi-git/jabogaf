package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.Door;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class MoveBlockDoor implements MoveBlockForMoveBehaviorCommon {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        boolean isBlocked = false;
        Optional<FieldConnection> fieldConnection = boardManager.getBoard().getLayout().getConnection(moveable.getPosition(), target);
        if (fieldConnection.isPresent()) {
            isBlocked = fieldConnection.get().getGameObjects().stream().
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
