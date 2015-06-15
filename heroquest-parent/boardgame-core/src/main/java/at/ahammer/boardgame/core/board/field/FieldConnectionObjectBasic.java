package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.core.object.GameObjectBasic;
import at.ahammer.boardgame.core.resource.MovePoint;

public abstract class FieldConnectionObjectBasic extends GameObjectBasic<FieldConnection> implements FieldConnectionObject {

    public FieldConnectionObjectBasic(String id) {
        this(id, null);
    }

    public FieldConnectionObjectBasic(String id, FieldConnection position) {
        super(id, position);
    }

    @Override
    public Resource movementCost() {
        return new MovePoint(0);
    }
}
