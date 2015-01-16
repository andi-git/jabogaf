package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.board.field.Field;

import java.util.Set;

/**
 * The strategy to check if the move is possible.
 */
@FunctionalInterface
public interface CanMoveStrategy {

    boolean canMove(Moveable moveable, Field target, Set<MoveBlock> moveBlocks);
}
