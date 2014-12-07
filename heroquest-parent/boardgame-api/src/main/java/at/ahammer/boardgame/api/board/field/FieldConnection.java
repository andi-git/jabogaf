package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.loader.ServiceLoader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a connection between two {@link Field}. On this connection there can be
 * multiple {@link FieldConnectionObject}s which are the behavior of the connection.
 */
public class FieldConnection extends GameContextBean {

    private final Field leftHand;

    private final Field rightHand;

    private final Set<FieldConnectionObject> fieldConnectionObjects = new HashSet<>();

    /**
     * Create a new {@link FieldConnection}
     *
     * @param id        the id of the {@link FieldConnection}
     * @param leftHand  the {@link at.ahammer.boardgame.api.artifact.Artifact} of the left hand
     * @param rightHand the {@link at.ahammer.boardgame.api.artifact.Artifact} of the right hand
     */
    public FieldConnection(String id, Field leftHand, Field rightHand) {
        super(id);
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    /**
     * Check if the current {@link FieldConnection} connects the two assigned {@link
     * Field}s
     *
     * @param field1 one {@link Field}
     * @param field2 another {@link Field}
     * @return {@code true} if the {@link FieldConnection} connects the 2 assigned
     * {@link Field}s
     */
    public boolean connects(Field field1, Field field2) {
        if (field1 == null || field2 == null) {
            return false;
        }
        return (field1.equals(leftHand) && field2.equals(rightHand)) || (field2.equals(leftHand) && field1.equals(rightHand));
    }

    public Set<FieldConnectionObject> getObjectsOnConnection() {
        return Collections.unmodifiableSet(fieldConnectionObjects);
    }

    public void addObjectOnConnection(FieldConnectionObject fieldConnectionObject) {
        fieldConnectionObjects.add(fieldConnectionObject);
    }

    public void addObjectOnConnection(FieldConnectionObject... fieldConnectionObject) {
        for (FieldConnectionObject f : fieldConnectionObject) {
            fieldConnectionObjects.add(f);
        }
    }

    public void clearObjectsOnConnection() {
        fieldConnectionObjects.clear();
    }

    public Field getRightHand() {
        return rightHand != null ? rightHand : getNullField();
//        return rightHand != null ? rightHand : new FieldNull();
    }

    public Field getLeftHand() {
        return leftHand != null ? leftHand : getNullField();
//        return leftHand != null ? leftHand : new FieldNull();
    }

    private Field getNullField() {
        return ServiceLoader.get(Field.class);
    }
}
