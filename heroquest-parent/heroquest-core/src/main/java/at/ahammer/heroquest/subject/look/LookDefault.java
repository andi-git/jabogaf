package at.ahammer.heroquest.subject.look;

import at.ahammer.boardgame.board.BoardAccess;
import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.subject.look.Look;
import at.ahammer.boardgame.subject.look.LookStrategy;
import at.ahammer.heroquest.object.field.Door;
import at.ahammer.heroquest.object.field.Wall;

import javax.inject.Inject;

/**
 * Created by andreas on 8/29/14.
 */
@GameScoped
@LookStrategy(LookDefault.class)
public class LookDefault implements Look {

    @Inject
    private BoardAccess boardAccess;

    @Override
    public boolean canLook(Field start, Field end) {
        boolean result = true;
        for(FieldConnection fieldConnection : boardAccess.getBoard().getLayout().getLookConnections(start, end)) {
            for (FieldConnectionObject fieldConnectionObject : fieldConnection.getObjectsOnConnection()) {
                if (fieldConnectionObject instanceof Wall) {
                    result = false;
                } else if (fieldConnectionObject instanceof Door && ((Door) fieldConnectionObject).isClosed()) {
                    result = false;
                }
                if (!result) {
                    break;
                }
                // TODO player, monster
            }
            if (!result) {
                break;
            }
        }
        return result;
    }
}