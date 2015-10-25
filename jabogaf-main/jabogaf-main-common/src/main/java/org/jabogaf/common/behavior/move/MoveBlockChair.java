package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.common.object.field.Chair;
import org.jabogaf.common.object.field.Wall;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoveBlockChair implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        FieldConnection fieldConnection = boardManager.getBoard().getLayout().getConnection(moveable.getPosition(), target);
        return fieldConnection != null &&
                !fieldConnection.getGameObjects().isEmpty() &&
                fieldConnection.getGameObjects().stream().anyMatch(o -> o instanceof Chair);
    }

    @Override
    public String toString() {
        return Wall.class.getSimpleName();
    }
}
