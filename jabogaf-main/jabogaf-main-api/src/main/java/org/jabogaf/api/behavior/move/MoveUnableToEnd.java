package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;

/**
 * Defines a strategy how a move can't end on a {@link Field}.
 */
@FunctionalInterface
public interface MoveUnableToEnd {

    /**
     * Check if the move is unable to end on the {@link Field}.
     *
     * @param moveable the {@link Moveable} to move
     * @param target   the {@link Field} to move to
     * @return {@code true} if the move is not able to end at the {@link Field}
     */
    boolean unableToEnd(Moveable moveable, Field target);
}
