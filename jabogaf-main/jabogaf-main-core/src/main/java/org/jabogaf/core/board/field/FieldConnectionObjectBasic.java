package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.state.GameState;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.state.GameStateNull;

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

    @Override
    public GameState getState() {
        return new GameStateNull();
    }
}
