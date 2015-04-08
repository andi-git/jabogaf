package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MovePath;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.resource.ResourceHolder;

import java.util.List;
import java.util.Map;

public interface MoveableFieldsCollector {

    List<MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder);
}
