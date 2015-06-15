package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a connection between two {@link at.ahammer.boardgame.api.board.field.Field}. On this connection there can
 * be multiple {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}s which are the behavior of the
 * connection.
 */
public class FieldConnectionBasic extends GameContextBeanBasic implements FieldConnection {

    private final Field leftHand;

    private final Field rightHand;

    private final Set<FieldConnectionObject> fieldConnectionObjects = new HashSet<>();

    /**
     * Create a new {@link at.ahammer.boardgame.core.board.field.FieldConnectionBasic}
     *
     * @param id        the id of the {@link at.ahammer.boardgame.core.board.field.FieldConnectionBasic}
     * @param leftHand  the {@link at.ahammer.boardgame.api.artifact.Artifact} of the left hand
     * @param rightHand the {@link at.ahammer.boardgame.api.artifact.Artifact} of the right hand
     */
    public FieldConnectionBasic(String id, Field leftHand, Field rightHand) {
        super(id);
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public boolean connects(Field field1, Field field2) {
        return !(field1 == null || field2 == null)
                && ((field1.equals(leftHand) && field2.equals(rightHand)) || (field2.equals(leftHand) && field1.equals(rightHand)));
    }

    @Override
    public Set<FieldConnectionObject> getObjectsOnConnection() {
        return Collections.unmodifiableSet(fieldConnectionObjects);
    }

    @Override
    public void addObjectOnConnection(FieldConnectionObject fieldConnectionObject) {
        fieldConnectionObjects.add(fieldConnectionObject);
    }

    @Override
    public void addObjectOnConnection(FieldConnectionObject... fieldConnectionObject) {
        Collections.addAll(fieldConnectionObjects, fieldConnectionObject);
    }

    @Override
    public void clearObjectsOnConnection() {
        fieldConnectionObjects.clear();
    }

    @Override
    public Field getRightHand() {
        return rightHand != null ? rightHand : new FieldNull();
    }

    @Override
    public Field getLeftHand() {
        return leftHand != null ? leftHand : new FieldNull();
    }

    @Override
    public boolean contains(Field field) {
        return leftHand.equals(field) || rightHand.equals(field);
    }
}
