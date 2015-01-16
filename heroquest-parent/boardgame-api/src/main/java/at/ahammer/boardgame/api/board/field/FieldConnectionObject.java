package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.object.GameObject;

/**
 * The class (or: multiple instances of this class) defines the behavior of a {@link
 * FieldConnection}.
 */
public abstract class FieldConnectionObject extends GameObject {

    /**
     * Create a new {@link FieldConnectionObject}
     *
     * @param id the id of the {@link FieldConnectionObject}
     */
    protected FieldConnectionObject(String id) {
        super(id);
    }
}
