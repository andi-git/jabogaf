package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.object.GameObjectBasic;
import org.jabogaf.core.resource.MovePoint;

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
