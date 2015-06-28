package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;

/**
 * Collect all {@link org.jabogaf.core.resource.MovePoint}s needed for the move
 */
public interface MovePointCollector {

    Resource collect(Moveable moveable, Field target);

    Resource collect(Field position, Field target);
}
