package at.ahammer.boardgame.entity.object.field;

import at.ahammer.boardgame.entity.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldConnection extends GameObject {

    private final Field leftHand;

    private final Field rightHand;

    private final Set<FieldConnectionObject> objectsOnConnection = new HashSet<>();

    public FieldConnection(Field leftHand, Field rightHand) {
        super();
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
}
