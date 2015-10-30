package org.jabogaf.api.behavior.look;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;

import java.util.Set;

/**
 * Encapsulates all information if the look is possible.
 */
public interface CanLookReport {

    Field getSource();

    Field getTarget();

    Resource cost();

    Resource maxPayment();

    boolean isPossible();

    boolean isBlocked();

    Set<LookBlock> lookBlocks();
}
