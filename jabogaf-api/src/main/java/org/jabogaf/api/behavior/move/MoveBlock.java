package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;

/**
 * Defines a strategy how a move is blocked.
 */
@FunctionalInterface
public interface MoveBlock {

    /**
     * Check if the move is blocked
     *
     * @param moveable the {@link Moveable} to move
     * @param target   the {@link Field} to move to
     * @return {@code true} if the move is BLOCKED (i.e. the move is not possitble)
     */
    boolean blocks(Moveable moveable, Field target);
}
