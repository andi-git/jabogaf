package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.subject.look.Look;
import at.ahammer.boardgame.subject.move.Move;

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
