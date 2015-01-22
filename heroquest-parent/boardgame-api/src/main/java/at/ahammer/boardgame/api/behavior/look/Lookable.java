package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.board.field.Field;

import java.util.Set;

/**
 * Every class that implements this interface can look to another position on the board.
 */
public interface Lookable {


    /**
     * Check if the {@link at.ahammer.boardgame.api.behavior.look.Lookable} can look from the current {@code position}
     * to another {@link at.ahammer.boardgame.api.board.field.Field} defined by the assigned {@code target}.
     * <p/>
     * The result is influenced by the available {@link at.ahammer.boardgame.api.behavior.look.LookBehavior}.
     *
     * @param target the {@link at.ahammer.boardgame.api.board.field.Field} to move to
     * @return {@code true} if the look is possible
     * @see at.ahammer.boardgame.api.behavior.look.LookBehavior#canLook(at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    boolean canLook(Field target);

    /**
     * Get a list of all {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to.
     *
     * @return a list of {@link at.ahammer.boardgame.api.board.field.Field}s that can be looked to
     * @see at.ahammer.boardgame.api.behavior.look.LookBehavior#getLookableFields(at.ahammer.boardgame.api.board.field.Field)
     */
    Set<Field> getLookableFields();

    LookBehavior getLookBehavior();

}