package at.ahammer.boardgame.api.board.field;

import java.util.HashSet;
import java.util.Set;

/**
 * The null-implementation of {@link FieldConnection}.
 */
public class FieldConnectionNull extends FieldConnection {

    public FieldConnectionNull() {
        super(String.valueOf(System.nanoTime()), new FieldNull(), new FieldNull());
    }

    @Override
    public boolean connects(Field leftHand, Field rightHand) {
        return false;
    }

    @Override
    public Set<FieldConnectionObject> getObjectsOnConnection() {
        return new HashSet<>();
    }

    @Override
    public void addObjectOnConnection(FieldConnectionObject fieldConnectionObject) {
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
