package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * The null-implementation of {@link FieldConnection}.
 */
public class FieldConnectionNull extends FieldConnectionBasic {

    public FieldConnectionNull() {
        super(randomId(), new FieldNull(), new FieldNull());
    }

    @Override
    public boolean connects(Field leftHand, Field rightHand) {
        return false;
    }

    @Override
    public Set<GameObject> getObjectsOnConnection() {
        return new HashSet<>();
    }

    @Override
    public void addObjectOnConnection(GameObject fieldConnectionObject) {
        // nothing
    }

    @Override
    public void clearObjectsOnConnection() {
        // nothing
    }

    @Override
    public Field getRightHand() {
        return new FieldNull();
    }

    @Override
    public Field getLeftHand() {
        return new FieldNull();
    }
}
