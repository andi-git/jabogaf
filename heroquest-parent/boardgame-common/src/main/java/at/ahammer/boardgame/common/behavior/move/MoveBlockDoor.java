package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.common.object.field.Door;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class MoveBlockDoor implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        FieldConnection fieldConnection = boardManager.getBoard().getLayout().getConnection(moveable.getPosition(), target);
        if (fieldConnection != null && !fieldConnection.getObjectsOnConnection().isEmpty()) {
            Stream<Door> doors = fieldConnection.getObjectsOnConnection().stream().filter(o -> o instanceof Door).map(o -> (Door) o);
            return doors.anyMatch(d -> d.isClosed());
        }
        return false;
    }

    @Override
    public String toString() {
        return Door.class.getSimpleName();
    }
}
