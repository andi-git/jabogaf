package at.ahammer.boardgame.api.behavior.look;

import at.ahammer.boardgame.api.board.field.Field;

/**
 * Defines a strategy how a look is blocked.
 */
@FunctionalInterface
public interface LookBlock {

    /**
     * Check if the look is blocked
     *
     * @param lookable the {@link Lookable} to move
     * @param target   the {@link Field} to move to
     * @return {@code true} if the look is BLOCKED (i.e. the look is not possitble)
     */
    boolean blocks(Lookable lookable, Field target);
}
