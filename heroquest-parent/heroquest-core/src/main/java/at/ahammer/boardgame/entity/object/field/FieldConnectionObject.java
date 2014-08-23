package at.ahammer.boardgame.entity.object.field;

import at.ahammer.boardgame.entity.object.GameObject;
import at.ahammer.boardgame.entity.subject.look.Look;
import at.ahammer.boardgame.entity.subject.move.Move;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldConnectionObject extends GameObject {

    protected FieldConnectionObject(String id) {
        super(id);
    }

    public abstract boolean canMove(Move move);

    public abstract boolean canLook(Look look);

}
