package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import java.util.Map;
import java.util.Set;

/**
 * The strategy of a move from one {@link at.ahammer.boardgame.api.board.field.Field} to another via {@link
 * #move(Moveable, at.ahammer.boardgame.api.subject.SetterOfPosition, at.ahammer.boardgame.api.board.field.Field,
 * at.ahammer.boardgame.api.resource.ResourceHolder)}
 * <p/>
 * With {@link #canMove(Moveable, at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.resource.ResourceHolder)}
 * it can be checked if it is possible to move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 */
public interface MoveBehavior {

    /**
     * Check if a move from {@code position} to {@code target} is possible. It is possible, that a {@link
     * at.ahammer.boardgame.api.object.GameObject} or {@link at.ahammer.boardgame.api.subject.GameSubject} blocks the
     * move.
     *
     * @param moveable       the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to move
     * @param target         the {@link at.ahammer.boardgame.api.board.field.Field} where the move ends
     * @param resourceHolder the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return {@code true} if the move is possible
     */
    boolean canMove(Moveable moveable, Field target, ResourceHolder resourceHolder);

    /**
     * Check if a move of a {@link at.ahammer.boardgame.api.behavior.move.MovePath} is possible. It is possible, that a
     * {@link at.ahammer.boardgame.api.object.GameObject} or {@link at.ahammer.boardgame.api.subject.GameSubject} blocks
     * the move.
     *
     * @param moveable       the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to move
     * @param movePath       the {@link at.ahammer.boardgame.api.behavior.move.MovePath} to move
     * @param resourceHolder the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return {@code true} if the move is possible
     */
    boolean canMove(Moveable moveable, MovePath movePath, ResourceHolder resourceHolder);

    /**
     * Move a {@link at.ahammer.boardgame.api.subject.GameSubject} to a {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param moveable         the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to move
     * @param setterOfPosition the function {@link at.ahammer.boardgame.api.subject.SetterOfPosition} to set the
     *                         position
     * @param target           the {@link at.ahammer.boardgame.api.board.field.Field} to move the {@link
     *                         at.ahammer.boardgame.api.behavior.move.Moveable} to
     * @param resourceHolder   the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return the new position of the {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Move a {@link at.ahammer.boardgame.api.subject.GameSubject} to a {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param moveable         the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to move
     * @param setterOfPosition the function {@link at.ahammer.boardgame.api.subject.SetterOfPosition} to set the
     *                         position
     * @param movePath         the {@link at.ahammer.boardgame.api.behavior.move.MovePath} to move the {@link
     *                         at.ahammer.boardgame.api.behavior.move.Moveable} along
     * @param resourceHolder   the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return the new position of the {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws FieldsNotConnectedException
     * @throws MoveNotPossibleException
     */
    Field move(Moveable moveable, SetterOfPosition setterOfPosition, MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException;

    /**
     * Get a map of all {@link at.ahammer.boardgame.api.board.field.Field}s that the {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} can be moved to from the current position. The keys of the map
     * are the {@link at.ahammer.boardgame.api.board.field.Field}s, the values are the {@link
     * at.ahammer.boardgame.api.resource.Resource}s (move-points) needed to move to that {@link
     * at.ahammer.boardgame.api.board.field.Field}.
     * <p/>
     * The result depends on the available {@link at.ahammer.boardgame.api.resource.Resource} (move-points) of the
     * {@link at.ahammer.boardgame.api.resource.ResourceHolder}.
     *
     * @param moveable       the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to check for
     * @param resourceHolder the available {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move
     * @return a set of all {@link at.ahammer.boardgame.api.board.field.Field}s that the {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} can be moved to from the current position
     */
    Map<Field, MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder);

    /**
     * Get the shortest path, e. g. the {@link at.ahammer.boardgame.api.behavior.move.MovePath} with less {@link
     * at.ahammer.boardgame.api.resource.Resource}s, from the current position of {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} to the target {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param moveable the {@link at.ahammer.boardgame.api.behavior.move.Moveable} to move
     * @param target   the target {@link at.ahammer.boardgame.api.board.field.Field}
     * @return the shortest path, e. g. the {@link at.ahammer.boardgame.api.behavior.move.MovePath} with less {@link
     * at.ahammer.boardgame.api.resource.Resource}s, from the current position of {@link
     * at.ahammer.boardgame.api.behavior.move.Moveable} to the target {@link at.ahammer.boardgame.api.board.field.Field}
     */
    MovePath getShortestPath(Moveable moveable, Field target);

    /**
     * Get a set of all {@link at.ahammer.boardgame.api.board.field.Field}s that the current player can be moved to from
     * the current position.
     *
     * @return a map of {@link at.ahammer.boardgame.api.board.field.Field}s that the current player can be moved to
     */
    Map<Field, MovePath> getMoveableFieldsForCurrent();

    /**
     * Get a {@link java.util.Set} of all {@link at.ahammer.boardgame.api.behavior.move.MoveBlock} that are activated.
     *
     * @return a {@link java.util.Set} of all {@link at.ahammer.boardgame.api.behavior.move.MoveBlock} that are
     * activated
     */
    Set<MoveBlock> getMoveBlocks();
}
