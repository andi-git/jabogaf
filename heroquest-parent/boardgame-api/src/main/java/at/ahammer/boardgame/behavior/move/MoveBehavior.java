package at.ahammer.boardgame.behavior.move;

import at.ahammer.boardgame.object.field.Field;

/**
 * The strategy of a move from one {@link at.ahammer.boardgame.object.field.Field} to another.
 * <p/>
 * With {@link #canMove(at.ahammer.boardgame.object.field.Field, at.ahammer.boardgame.object.field.Field)} it can be
 * checked if it is possible to move from one {@link at.ahammer.boardgame.object.field.Field} to another.
 */
public interface MoveBehavior {

    /**
     * Check if a move from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.object.GameObject} or {@link at.ahammer.boardgame.subject.GameSubject} blocks the move.
     *
     * @param position the {@link at.ahammer.boardgame.object.field.Field} where to move starts
     * @param target   the {@link at.ahammer.boardgame.object.field.Field} where the move ends
     * @return {@code true} if the move is possible
     */
    boolean canMove(Field position, Field target);
}
