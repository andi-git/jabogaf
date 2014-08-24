package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldConnection extends GameObject {

    private final Field leftHand;

    private final Field rightHand;

    private final Set<FieldConnectionObject> objectsOnConnection = new HashSet<>();

    public FieldConnection(String id, Field leftHand, Field rightHand) {
        super(id);
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public boolean connect(Field field1, Field field2) {
        return (field1.equals(leftHand) && field2.equals(rightHand)) || (field2.equals(leftHand) && field1.equals(rightHand));
    }

    public Set<FieldConnectionObject> getObjectsOnConnection() {
        return objectsOnConnection;
    }

    public void addObjectOnConnection(FieldConnectionObject fieldConnectionObject) {
        objectsOnConnection.add(fieldConnectionObject);
    }

    public void clearObjectsOnConnection() {
        objectsOnConnection.clear();
    }

    public Field getRightHand() {
        return rightHand;
    }

    public Field getLeftHand() {
        return leftHand;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
