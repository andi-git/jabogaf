package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.object.field.FieldConnectionObject;
import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.move.MoveBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public class Wall extends FieldConnectionObject {

    public Wall(String id) {
        super(id);
    }

    @Override
    public boolean canMove(MoveBehavior moveBehavior) {
        return false;
    }

    @Override
    public boolean canLook(LookBehavior lookBehavior) {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
