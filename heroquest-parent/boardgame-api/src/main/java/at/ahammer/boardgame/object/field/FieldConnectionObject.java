package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.move.MoveBehavior;
import at.ahammer.boardgame.object.GameObject;

/**
 * The class (or: multiple instances of this class) defines the behavior of a {@link
 * at.ahammer.boardgame.object.field.FieldConnection}.
 */
public abstract class FieldConnectionObject extends GameObject {

    /**
     * Create a new {@link at.ahammer.boardgame.object.field.FieldConnectionObject}
     *
     * @param id the id of the {@link at.ahammer.boardgame.object.field.FieldConnectionObject}
     */
    protected FieldConnectionObject(String id) {
        super(id);
    }

    /**
     * Check if the move based on a {@link at.ahammer.boardgame.behavior.move.MoveBehavior} is possible or is blocked by
     * the {@link at.ahammer.boardgame.object.field.FieldConnectionObject}.
     *
     * @param moveBehavior the {@link at.ahammer.boardgame.behavior.move.MoveBehavior} to move from one {@link
     *                     at.ahammer.boardgame.object.field.Field} to another
     * @return {@code true} if the move is possible
     */
    public abstract boolean canMove(MoveBehavior moveBehavior);

    /**
     * Check if the look based on a {@link at.ahammer.boardgame.behavior.look.LookBehavior} is possible or is blocked by
     * the {@link at.ahammer.boardgame.object.field.FieldConnectionObject}.
     *
     * @param lookBehavior the {@link at.ahammer.boardgame.behavior.look.LookBehavior} to move from one {@link
     *                     at.ahammer.boardgame.object.field.Field} to another
     * @return {@code true} if the move is possible
     */
    public abstract boolean canLook(LookBehavior lookBehavior);
}
