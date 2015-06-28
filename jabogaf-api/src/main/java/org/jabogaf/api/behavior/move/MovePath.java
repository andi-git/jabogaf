package org.jabogaf.api.behavior.move;


import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;

import java.util.List;

/**
 * Representation of a path (multiple {@link Field} between position {@link Field} and target {@link Field}. It has also
 * knowledge about the cost of the {@link Resource}s to perform the move.
 */
public interface MovePath {

    /**
     * The current position {@link Field} where the path starts.
     *
     * @return the current position {@link Field} where the path starts
     */
    Field getPosition();

    /**
     * The reachable target {@link Field} where the path ends.
     *
     * @return the reachable target {@link Field} where the path ends
     */
    Field getTarget();

    /**
     * All {@link Field}s that represents the path from position to target. The position is not included in this list
     * otherwise the target is included.
     * <p/>
     * The list is sorted started with the first {@link Field} to move to starting from the position. So the last
     * element is the target.
     *
     * @return all {@link Field}s that represents the path from position to target
     */
    List<Field> getPathFields();

    /**
     * Get the overall cost as {@link Resource} to move from the position to the target.
     *
     * @return the overall cost as {@link Resource} to move from the position to the target
     */
    Resource cost();

    boolean contains(Field field);

    void update(MovePath movePath);
}