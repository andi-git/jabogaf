package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfPosition;

import java.util.List;
import java.util.Set;

/**
 * The strategy of a move from one {@link Field} to another via {@link #move(Moveable, SetterOfPosition, Field,
 * ResourceHolder)}
 * <p/>
 * With {@link #canMove(Moveable, Field, ResourceHolder)} it can be checked if it is possible to move from one {@link
 * Field} to another.
 */
public interface MoveBehavior {

    /**
     * Check if a move from {@code position} to {@code target} is possible. It is possible, that a {@link GameObject} or
     * {@link GameSubject} blocks the move.
     *
     * @param moveable       the {@link Moveable} to move
     * @param target         the {@link Field} where the move ends
     * @param resourceHolder the available {@link Resource}s to perform the move
     * @return the {@link CanMoveReport} of the move
     */
    CanMoveReport canMove(Moveable moveable, Field target, ResourceHolder resourceHolder);

    /**
     * Check if a move of a {@link MovePath} is possible. It is possible, that a {@link GameObject} or {@link
     * GameSubject} blocks the move.
     *
     * @param moveable       the {@link Moveable} to move
     * @param movePath       the {@link MovePath} to move
     * @param resourceHolder the available {@link Resource}s to perform the move
     * @return {@code true} if the move is possible
     */
    boolean canMove(Moveable moveable, org.jabogaf.api.behavior.move.MovePath movePath, ResourceHolder resourceHolder);

    /**
     * Move a {@link GameSubject} to a {@link Field}.
     *
     * @param moveable         the {@link Moveable} to move
     * @param setterOfPosition the function {@link SetterOfPosition} to set the position
     * @param target           the {@link Field} to move the {@link Moveable} to
     * @param resourceHolder   the available {@link Resource}s to perform the move
     * @return the new position of the {@link GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Move a {@link GameSubject} to a {@link Field}.
     *
     * @param moveable         the {@link Moveable} to move
     * @param setterOfPosition the function {@link SetterOfPosition} to set the position
     * @param movePath         the {@link MovePath} to move the {@link Moveable} along
     * @param resourceHolder   the available {@link Resource}s to perform the move
     * @return the new position of the {@link GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(Moveable moveable, SetterOfPosition setterOfPosition, MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Get a {@link List} of all {@link MovePath}s that the {@link Moveable} can be moved to from the current position.
     * <p/>
     * The result depends on the available {@link Resource} (move-points) of the {@link ResourceHolder}.
     *
     * @param moveable       the {@link Moveable} to check for
     * @param resourceHolder the available {@link Resource}s to perform the move
     * @return a set of all {@link Field}s that the {@link Moveable} can be moved to from the current position
     */
    List<MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder);

    /**
     * Get the shortest path, e. g. the {@link MovePath} with less {@link Resource}s, from the current position of
     * {@link Moveable} to the target {@link Field}.
     *
     * @param moveable       the {@link Moveable} to move
     * @param target         the target {@link Field}
     * @param resourceHolder the available {@link Resource}s to perform the move
     * @return the shortest path, e. g. the {@link MovePath} with less {@link Resource}s, from the current position of
     * {@link Moveable} to the target {@link Field}
     */
    MovePath getShortestPath(Moveable moveable, Field target, ResourceHolder resourceHolder);

    /**
     * Get a list of all {@link MovePath}s that the current player can be moved to from the current position.
     *
     * @return a list of {@link MovePath}s that the current player can be moved to
     */
    List<MovePath> getMoveableFieldsForCurrent();

    /**
     * Get a {@link Set} of all {@link MoveBlock}s that are activated.
     *
     * @return a {@link Set} of all {@link MoveBlock}s that are activated
     */
    Set<MoveBlock> getMoveBlocks();

    /**
     * Get a {@link Set} of {@link MoveBlock} that blocks the move of a {@link Moveable} to the target {@link Field}
     *
     * @param moveable the {@link Moveable} that want's to move
     * @param target   the target {@link Field} to move to
     * @return a {@link Set} of {@link MoveBlock} that block the move of a {@link Moveable} to the target {@link Field}
     */
    Set<MoveBlock> checkMoveBlocks(Moveable moveable, Field target);

    /**
     * Get a {@link Set} of all {@link MoveUnableToEnd}s that are activated.
     *
     * @return a {@link Set} of all {@link MoveUnableToEnd}s that are activated
     */
    Set<MoveUnableToEnd> getMoveUnableToEnd();

    /**
     * Get a {@link Set} of {@link MoveUnableToEnd} that makes it unable for the {@link Moveable} to end up on this {@link Field}.
     *
     * @param moveable the {@link Moveable} that want's to move
     * @param target   the target {@link Field} to move to
     * @return a {@link Set} of {@link MoveUnableToEnd} that makes it unable for the {@link Moveable} to end up on this {@link Field}
     */
    Set<MoveUnableToEnd> checkMoveUnableToEnd(Moveable moveable, Field target);
}
