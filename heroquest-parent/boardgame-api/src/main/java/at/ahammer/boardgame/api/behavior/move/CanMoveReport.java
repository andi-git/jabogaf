package at.ahammer.boardgame.api.behavior.move;

import at.ahammer.boardgame.api.resource.Resource;

import java.util.Set;

/**
 * Encapsulates all information if the move is possible.
 */
public interface CanMoveReport {

    boolean isPossible();

    boolean canPay();

    Resource moveCost();

    Resource maxPayment();

    boolean isBlocked();

    Set<MoveBlock> moveBlocks();
}
