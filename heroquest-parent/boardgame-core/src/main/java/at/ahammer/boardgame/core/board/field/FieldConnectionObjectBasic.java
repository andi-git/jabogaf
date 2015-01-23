package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.object.GameObjectBasic;
import at.ahammer.boardgame.core.resource.MovePoint;

public abstract class FieldConnectionObjectBasic extends GameObjectBasic implements FieldConnectionObject {

    public FieldConnectionObjectBasic(String id) {
        super(id);
    }

    @Override
    public Resource movementCost() {
        return new MovePoint(0);
    }

}
