package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.api.subject.GameSubject;

import java.util.Set;

/**
 * The strategy of a look from one {@link Field} to another,
 * <p/>
 * With {@link #canLook(Lookable, Field)} it can be checked if it is possible to look from one {@link Field} to
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
    CanLookReport canLook(Lookable lookable, Field target);

    /**
     * Get a set of all {@link Field}s that can be looked to.
     *
     * @param lookable the {@link Lookable} to check for
     * @return a set of {@link Field}s that can be looked to
     */
    Set<Field> getLookableFields(Lookable lookable);

    /**
     * Get a {@link Set} of all {@link LookBlock}s that are activated.
     *
     * @return a {@link Set} of all {@link LookBlock}s that are activated
     */
    Set<MoveBlock> getLookBlocks();

}
