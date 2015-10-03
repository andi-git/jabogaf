package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.SetterOfPosition;

import java.util.List;

/**
 * Every class that implements this interface can move on the board, i.e. change it's position.
 */
public interface Moveable {

    /**
     * Get the current position as {@link Field}.
     *
     * @return the current position as {@link Field}
     */
    Field getPosition();

    /**
     * Get the {@link MoveBehavior} of the {@link Moveable}.
     *
     * @return the {@link MoveBehavior}
     */
    MoveBehavior getMoveBehavior();

    /**
     * Move the {@link Moveable} from the current {@code position} to a {@link Field} defined by the parameter {@code
     * target}.
     * <p/>
     * The result is influenced by the available {@link MoveBehavior}.
     *
     * @param target         the {@link Field} to move the {@link Moveable} to
     * @param resourceHolder the {@link ResourceHolder} to pay for the move
     * @return the new position as {@link Field}
     * @throws FieldsNotConnectedException if the {@link Field}s {@code position} and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link GameObject} or something
     * @see MoveBehavior#move(Moveable, SetterOfPosition, Field, ResourceHolder)
     */
    Field move(Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Move the {@link Moveable} from the current {@code position} to a {@link Field} defined by the parameter {@code
     * target}.
     * <p/>
     * The result is influenced by the available {@link MoveBehavior}.
     *
     * @param movePath       the {@link MovePath} to move the {@link Moveable} along
     * @param resourceHolder the {@link ResourceHolder} to pay for the move
     * @return the new position as {@link Field}
     * @throws FieldsNotConnectedException if the {@link Field}s {@code position} and {@code target} are not connected
     * @throws MoveNotPossibleException    if the move from {@code position} to {@code target} is not possible, because
     *                                     it is blocked by a {@link GameObject} or something
     * @see MoveBehavior#move(Moveable, SetterOfPosition, Field, ResourceHolder)
     */
    Field move(MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Check if the {@link Moveable} can move from the current {@code position} to another {@link Field} defined by the
     * assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link MoveBehavior}.
     *
     * @param target         the {@link Field} to move to
     * @param resourceHolder the {@link ResourceHolder} to pay for the move
     * @return {@code true} if the move is possible
     * @see MoveBehavior#canMove(Moveable, Field, ResourceHolder)
     */
    CanMoveReport canMove(Field target, ResourceHolder resourceHolder);

    /**
     * Check if the {@link Moveable} can move from the current {@code position} to another {@link Field} defined by the
     * assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link MoveBehavior}.
     *
     * @param movePath       the {@link MovePath} to move the {@link Moveable} along
     * @param resourceHolder the {@link ResourceHolder} to pay for the move
     * @return {@code true} if the move is possible
     * @see MoveBehavior#canMove(Moveable, Field, ResourceHolder)
     */
    boolean canMove(MovePath movePath, ResourceHolder resourceHolder);

    /**
     * Get a map of all {@link Field}s that can be moved to where the keys are the {@link Field} and the values are the
     * costs of the move as {@link Resource}.
     *
     * @return a list of {@link Field}s that can be moved to
     * @see MoveBehavior#getMovableFields(Moveable, ResourceHolder)
     */
    List<MovePath> getMovableFields(ResourceHolder resourceHolder);

    /**
     * Get the shortest path, e. g. the {@link MovePath} with less {@link Resource}s, from the current position of
     * {@link Moveable} to the target {@link Field}.
     *
     * @param target         the target {@link Field}
     * @param resourceHolder the available {@link Resource}s to perform the move
     * @return the shortest path, e. g. the {@link MovePath} with less {@link Resource}s, from the current position of
     * {@link Moveable} to the target {@link Field}
     * @see MoveBehavior#getShortestPath(Moveable, Field, ResourceHolder)
     */
    MovePath getShortestPath(Field target, ResourceHolder resourceHolder);

    Moveable cloneMoveable();

    Moveable cloneMoveable(Field field);

    /**
     * Check if a move is able to end on a {@link Field}
     *
     * @param field the {@link Field} to move to
     * @return {@code true} if it is able to end a move on the assigned {@link Field}
     */
    boolean isMoveableTarget(Field field);
}