package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.Resource;

/**
 * Collect all {@link at.ahammer.boardgame.core.resource.MovePoint}s needed for the move
 */
public interface MovePointCollector {

    Resource collect(Moveable moveable, Field target);

    Resource collect(Field position, Field target);
}
