package org.jabogaf.api.behavior.look;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;

import java.util.List;

/**
 * The path of all {@link LayoutActionImpact} to look from one {@link Field} to another {@link Field}.
 */
public interface LookPath extends GameContextBean<LookPath> {

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
     * Get all {@link LayoutActionImpact}s that are within the look-path.
     *
     * @return all {@link LayoutActionImpact}s that are within the look-path
     */
    List<LayoutActionImpact<?, ?>> getLayoutActionImpacts();

    /**
     * Get the overall cost as {@link Resource} to move from the position to the target.
     *
     * @return the overall cost as {@link Resource} to move from the position to the target
     */
    Resource cost();

    /**
     * Get all {@link Field}s on this {@link LookPath}.
     *
     * @return all {@link Field}s on this {@link LookPath}
     */
    List<Field> getFields();
}
