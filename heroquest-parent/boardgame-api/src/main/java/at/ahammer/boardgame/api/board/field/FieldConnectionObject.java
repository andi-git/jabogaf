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

    /**
     * Check if the move based on a {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} is possible or is blocked by
     * the {@link FieldConnectionObject}.
     *
     * @param moveBehavior the {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} to move from one {@link
     *                     Field} to another
     * @return {@code true} if the move is possible
     */
    public abstract boolean canMove(MoveBehavior moveBehavior);

    /**
     * Check if the look based on a {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} is possible or is blocked by
     * the {@link FieldConnectionObject}.
     *
     * @param lookBehavior the {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} to move from one {@link
     *                     Field} to another
     * @return {@code true} if the move is possible
     */
    public abstract boolean canLook(LookBehavior lookBehavior);
}
