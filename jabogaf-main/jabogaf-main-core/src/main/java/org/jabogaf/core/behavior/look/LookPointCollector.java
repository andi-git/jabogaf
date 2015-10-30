package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;

/**
 * Collect all {@link org.jabogaf.core.resource.MovePoint}s needed for the move
 */
public interface LookPointCollector {

    Resource collect(Lookable lookable, Field target);

    Resource collect(Field position, Field target);
}
