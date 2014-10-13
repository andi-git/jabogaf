package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.object.field.Field;

import javax.ws.rs.Produces;
import java.util.Set;

/**
 * Created by andreas on 8/29/14.
 */
@GameScoped
public class BoardAccess {

    public Board getBoard() {
        return GameContext.current().getNewInstanceInGameContext(Board.class);
    }

    public Set<Field> getFields() {
        return getBoard().getLayout().getFields();
    }

    public Set<GameObject> getGameObjects(Field field) {
        return getBoard().getLayout().getAllGameObjectsOf(field);
    }
}
