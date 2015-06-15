package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.board.field.Field;

import java.util.Set;

/**
 * Every class that implements this interface can look to another position on the board.
 */
public interface Lookable {

    /**
     * Get the current position as {@link Field}.
     *
     * @return the current position as {@link Field}
     */
    Field getPosition();

    /**
     * Check if the {@link Lookable} can look from the current {@code position} to another {@link Field} defined by the
     * assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link LookBehavior}.
     *
     * @param target the {@link Field} to move to
     * @return a {@link CanLookReport} about the look, e.g. if the look is possible
     * @see LookBehavior#canLook(Lookable, Field)
     */
    CanLookReport canLook(Field target);

    /**
     * Get a list of all {@link Field}s that can be looked to.
     *
     * @return a list of {@link Field}s that can be looked to
     * @see LookBehavior#getLookableFields(Lookable)
     */
    Set<Field> getLookableFields();

    LookBehavior getLookBehavior();

}