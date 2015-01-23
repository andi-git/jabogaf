package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.api.resource.Resource;

/**
 * The class (or: multiple instances of this class) defines the behavior of a {@link
 * FieldConnection}.
 */
public interface FieldConnectionObject extends GameObject {

    Resource movementCost();
}
