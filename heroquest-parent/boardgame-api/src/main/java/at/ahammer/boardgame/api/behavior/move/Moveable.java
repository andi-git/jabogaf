package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.ResourceHolder;

import java.util.List;
import java.util.Map;

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
     * @param target         the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *                       at.ahammer.boardgame.api.behavior.move.Moveable} to
     * @param resourceHolder the {@link at.ahammer.boardgame.api.resource.ResourceHolder} to pay for the move
     * @return the new position as {@link at.ahammer.boardgame.api.board.field.Field}
     * @throws FieldsNotConnectedException if the {@link at.ahammer.boardgame.api.board.field.Field}s {@code position}
     *                                     and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or
     *                                     something
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#move(Moveable, at.ahammer.boardgame.api.subject.SetterOfPosition,
     * at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    Field move(Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Move the {@link at.ahammer.boardgame.api.behavior.move.Moveable} from the current {@code position} to a {@link
     * at.ahammer.boardgame.api.board.field.Field} defined by the parameter {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param movePath       the {@link at.ahammer.boardgame.api.behavior.move.MovePath} to move the {@link
     *                       at.ahammer.boardgame.api.behavior.move.Moveable} along
     * @param resourceHolder the {@link at.ahammer.boardgame.api.resource.ResourceHolder} to pay for the move
     * @return the new position as {@link at.ahammer.boardgame.api.board.field.Field}
     * @throws FieldsNotConnectedException if the {@link at.ahammer.boardgame.api.board.field.Field}s {@code position}
     *                                     and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link at.ahammer.boardgame.api.object.GameObject} or
     *                                     something
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#move(Moveable, at.ahammer.boardgame.api.subject.SetterOfPosition,
     * at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    Field move(MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Check if the {@link at.ahammer.boardgame.api.behavior.move.Moveable} can move from the current {@code position}
     * to another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param target         the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @param resourceHolder the {@link at.ahammer.boardgame.api.resource.ResourceHolder} to pay for the move
     * @return {@code true} if the move is possible
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#canMove(Moveable, at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    CanMoveReport canMove(Field target, ResourceHolder resourceHolder);

    /**
     * Check if the {@link at.ahammer.boardgame.api.behavior.move.Moveable} can move from the current {@code position}
     * to another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior}.
     *
     * @param movePath       the {@link at.ahammer.boardgame.api.behavior.move.MovePath} to move the {@link
     *                       at.ahammer.boardgame.api.behavior.move.Moveable} along
     * @param resourceHolder the {@link at.ahammer.boardgame.api.resource.ResourceHolder} to pay for the move
     * @return {@code true} if the move is possible
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#canMove(Moveable, at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    boolean canMove(MovePath movePath, ResourceHolder resourceHolder);

    /**
     * Get a map of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to where the keys are the
     * {@link at.ahammer.boardgame.api.board.field.Field} and the values are the costs of the move as {@link
     * at.ahammer.boardgame.api.resource.Resource}.
     *
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be moved to
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#getMovableFields(Moveable,
     * at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    List<MovePath> getMovableFields(ResourceHolder resourceHolder);

    /**
     * Get the shortest path, e. g. the {@link at.ahammer.boardgame.api.behavior.move.MovePath} with less {@link
     * at.ahammer.boardgame.api.resource.Resource}s, from the current position of {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} to the target {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param target         the target {@link at.ahammer.boardgame.api.board.field.Field}
     * @param resourceHolder the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return the shortest path, e. g. the {@link at.ahammer.boardgame.api.behavior.move.MovePath} with less {@link
     * at.ahammer.boardgame.api.resource.Resource}s, from the current position of {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} to the target {@link at.ahammer.boardgame.api.board.field.Field}
     * @see at.ahammer.boardgame.api.behavior.move.MoveBehavior#getShortestPath(Moveable,
     * at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.resource.ResourceHolder)
     */
    MovePath getShortestPath(Field target, ResourceHolder resourceHolder);

    Moveable cloneMoveable();

    Moveable cloneMoveable(Field field);
}