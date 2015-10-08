package org.jabogaf.api.behavior.move;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;

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

    boolean isAbleToEnd();

    Set<MoveUnableToEnd> moveUnableToEnd();

    Field getSource();

    Field getTarget();
}
