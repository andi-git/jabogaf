package at.ahammer.heroquest.behavior.move;

import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.behavior.move.MoveBehavior;
import at.ahammer.heroquest.object.field.Door;
import at.ahammer.heroquest.object.field.Wall;

/**
 * Created by andreas on 8/22/14.
 */
@GameScoped
public class MoveBehaviorHero implements MoveBehavior {

    @Override
    public boolean canMove(Field start, Field end) {
        if (start.isConnected(end)) {
            FieldConnection fieldConnection = start.getConnectionTo(end);
            // doors only if it is opened
            for (FieldConnectionObject fieldConnectionObject : fieldConnection.getObjectsOnConnection()) {
                if (fieldConnectionObject instanceof Wall) {
                    return false;
                } else if (fieldConnectionObject instanceof Door) {
                    return !((Door) fieldConnectionObject).isClosed();
                } else {
                    return true;
                }
            }
            return true;
            // no monsters
//            if () {
//
//            }
        }
        return false;
    }
}
