package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.entity.object.field.FieldConnectionObject;
import at.ahammer.boardgame.entity.subject.look.Look;
import at.ahammer.boardgame.entity.subject.move.Move;

/**
 * Created by andreas on 8/14/14.
 */
public class Wall extends FieldConnectionObject {

    @Override
    public boolean canMove(Move move) {
        return false;
    }

    @Override
    public boolean canLook(Look look) {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
