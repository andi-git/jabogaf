package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.move.MoveBehavior;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldConnectionObject extends GameObject {

    protected FieldConnectionObject(String id) {
        super(id);
    }

    public abstract boolean canMove(MoveBehavior moveBehavior);

    public abstract boolean canLook(LookBehavior lookBehavior);

}
