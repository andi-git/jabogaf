package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;

import java.util.List;

/**
 * The strategy of a move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 * <p/>
 * With {@link #canMove(at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.board.field.Field)} it can
 * be checked if it is possible to move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 */
public interface MoveBehavior {

    /**
     * Check if a move from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.api.object.GameObject} or {@link at.ahammer.boardgame.api.subject.GameSubject} blocks the
     * move.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to move starts
     * @param target   the {@link at.ahammer.boardgame.api.board.field.Field} where the move ends
     * @return {@code true} if the move is possible
     */
    boolean canMove(Field position, Field target);

    /**
     * Move a {@link at.ahammer.boardgame.api.subject.GameSubject} to a {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param gameSubject the {@link at.ahammer.boardgame.api.subject.GameSubject} to move
     * @param target      the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *                    at.ahammer.boardgame.api.subject.GameSubject} to
     * @return the new position of the {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(GameSubject gameSubject, Field target) throws FieldsNotConnectedException, MoveNotPossibleException;

    /**
     * Get a list of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to move starts
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to
     */
    List<Field> getMovableFields(Field position);
}
