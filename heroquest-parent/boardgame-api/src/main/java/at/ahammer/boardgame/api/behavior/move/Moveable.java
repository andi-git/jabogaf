package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;

import java.util.Set;

/**
 * Every class that implements this interface can move on the board, i.e. change it's position.
 */
public interface Moveable {

    /**
     * Get the current position as {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @return the current position as {@link at.ahammer.boardgame.api.board.field.Field}
     */
    Field getPosition();

    /**
     * Get the {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} of the {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable}.
     *
     * @return the {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}
     */
    MoveBehavior getMoveBehavior();

    /**
     * Move the {@link at.ahammer.boardgame.api.behavior.move.Moveable} from the current {@code position} to a {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the parameter {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *               at.ahammer.boardgame.api.behavior.move.Moveable} to
     * @return the new position as {@link at.ahammer.boardgame.api.board.field.Field}
     * @throws FieldsNotConnectedException if the {@link at.ahammer.boardgame.api.board.field.Field}s {@code position}
     *                                     and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or
     *                                     something
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#move(at.ahammer.boardgame.api.subject.SetterOfPosition,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException;

    /**
     * Check if the {@link at.ahammer.boardgame.api.behavior.move.Moveable} can move from the current {@code position}
     * to another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the move is possible
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#canMove(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    boolean canMove(Field target);

    /**
     * Get a list of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to.
     *
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#getMovableFields(at.ahammer.boardgame.api.board.field.Field)
     */
    public Set<Field> getMovableFields();
}