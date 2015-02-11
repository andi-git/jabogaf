package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.Resource;

import java.util.List;

/**
 * Representation of a path (multiple {@link at.ahammer.boardgame.api.board.field.Field} between position {@link
 * at.ahammer.boardgame.api.board.field.Field} and target {@link at.ahammer.boardgame.api.board.field.Field}. It has
 * also knowledge about the cost of the {@link at.ahammer.boardgame.api.resource.Resource}s to perform the move.
 */
public interface MovePath {

    /**
     * The current position {@link at.ahammer.boardgame.api.board.field.Field} where the path starts.
     *
     * @return the current position {@link at.ahammer.boardgame.api.board.field.Field} where the path starts
     */
    Field getPosition();

    /**
     * The reachable target {@link at.ahammer.boardgame.api.board.field.Field} where the path ends.
     *
     * @return the reachable target {@link at.ahammer.boardgame.api.board.field.Field} where the path ends
     */
    Field getTarget();

    /**
     * All {@link at.ahammer.boardgame.api.board.field.Field}s that represents the path from position to target. The
     * position is not included in this list otherwise the target is included.
     * <p/>
     * The list is sorted started with the first {@link at.ahammer.boardgame.api.board.field.Field} to move to starting
     * from the position. So the last element is the target.
     *
     * @return all {@link at.ahammer.boardgame.api.board.field.Field}s that represents the path from position to target
     */
    List<Field> getPathFields();

    /**
     * Get the overall cost as {@link at.ahammer.boardgame.api.resource.Resource} to move from the position to the
     * target.
     *
     * @return the overall cost as {@link at.ahammer.boardgame.api.resource.Resource} to move from the position to the
     * target
     */
    Resource cost();

    boolean contains(Field field);

    void update(MovePath movePath);
}