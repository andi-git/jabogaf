package org.jabogaf.api.behavior.look;

import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.GameSubject;

import java.util.List;
import java.util.Set;

/**
 * The strategy of a look from one {@link Field} to another,
 * <p>
 * With {@link #canLook(Lookable, Field, ResourceHolder)} it can be checked if it is possible to look from one {@link Field} to
 * another.
 */
public interface LookBehavior {

    /**
     * Check if a look from {@code position} to {@code target} is possible. It is possible, that a {@link GameObject} or
     * {@link GameSubject} blocks the look.
     *
     * @param lookable the {@link Lookable} to check for
     * @param target   the {@link Field} where the look ends
     * @return the {@link CanLookReport} that knows if the look is possible
     */
    CanLookReport canLook(Lookable lookable, Field target, ResourceHolder resourceHolder);

    /**
     * Get a set of all {@link Field}s that can be looked to.
     *
     * @param lookable the {@link Lookable} to check for
     * @return a set of {@link Field}s that can be looked to
     */
    List<Field> getLookableFields(Lookable lookable, ResourceHolder resourceHolder);

    /**
     * Get a {@link Set} of all {@link LookBlock}s that are activated.
     *
     * @return a {@link Set} of all {@link LookBlock}s that are activated
     */
    Set<LookBlock> getLookBlocks();

    /**
     * Get a {@link Set} of {@link LookBlock} that blocks the look of a {@link Lookable} to the target {@link Field}
     *
     * @param lookable the {@link Lookable} that want's to look
     * @param target   the target {@link Field} to look to
     * @return a {@link Set} of {@link LookBlock} that block the look of a {@link Lookable} to the target {@link Field}
     */
    Set<LookBlock> checkLookBlocks(Lookable lookable, Field target);
}
