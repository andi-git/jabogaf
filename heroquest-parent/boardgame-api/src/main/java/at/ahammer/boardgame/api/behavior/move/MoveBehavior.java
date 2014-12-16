package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import java.util.Set;

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
     * @param setterOfPosition the function {@link at.ahammer.boardgame.api.subject.SetterOfPosition} to set the
     *                         position
     * @param target           the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *                         at.ahammer.boardgame.api.subject.GameSubject} to
     * @return the new position of the {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(SetterOfPosition setterOfPosition, Field target) throws FieldsNotConnectedException, MoveNotPossibleException;

    /**
     * Get a set of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to.
     *
     * @param position the {@link at.ahammer.boardgame.api.board.field.Field} where to move starts
     * @return a set of {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to
     */
    Set<Field> getMovableFields(Field position);

    /**
     * Check if the current {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} can be used on a {@link
     * at.ahammer.boardgame.api.board.Layout}.
     *
     * @return {@code true} if the current {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} can be used on a
     * {@link at.ahammer.boardgame.api.board.Layout}
     */
    boolean canBeUsedOn(Layout layout);

}
