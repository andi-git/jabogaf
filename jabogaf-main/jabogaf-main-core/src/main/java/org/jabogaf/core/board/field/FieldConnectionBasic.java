package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import java.util.*;

/**
 * Represents a connection between two {@link Field}. On this connection there can be multiple {@link GameObject}s which
 * are the behavior of the connection.
 */
public class FieldConnectionBasic extends GameContextBeanBasic<FieldConnection> implements FieldConnection {

    private final Field leftHand;

    private final Field rightHand;

    private final List<GameObject<? extends ContainsGameObjects>> fieldConnectionObjects = new ArrayList<>();

    /**
     * Create a new {@link org.jabogaf.core.board.field.FieldConnectionBasic}
     *
     * @param id        the id of the {@link org.jabogaf.core.board.field.FieldConnectionBasic}
     * @param leftHand  the {@link org.jabogaf.api.artifact.Artifact} of the left hand
     * @param rightHand the {@link org.jabogaf.api.artifact.Artifact} of the right hand
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
    public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
        return Collections.unmodifiableList(fieldConnectionObjects);
    }

    @Override
    public void addObjectOnConnection(GameObject<? extends ContainsGameObjects> fieldConnectionObject) {
        fieldConnectionObjects.add(fieldConnectionObject);
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

    @Override
    public boolean containsAny(Collection<Field> fields) {
        return fields.contains(leftHand) || fields.contains(rightHand);
    }

    @Override
    public boolean containsAll(Collection<Field> fields) {
        return fields.contains(leftHand) && fields.contains(rightHand);
    }
}
