package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;

/**
 * The strategy of a move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 * <p/>
 * With {@link #canMove(at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.board.field.Field)} it can be
 * checked if it is possible to move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 */
public interface MoveBehavior {

    /**
     * Check if a move from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.api.object.GameObject} or {@link at.ahammer.boardgame.api.subject.GameSubject} blocks the move.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to move starts
     * @param target   the {@link at.ahammer.boardgame.api.board.field.Field} where the move ends
     * @return {@code true} if the move is possible
     */
    boolean canMove(Field position, Field target);
}
