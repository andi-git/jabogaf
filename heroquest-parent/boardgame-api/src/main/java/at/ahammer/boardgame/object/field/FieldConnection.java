package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a connection between two {@link at.ahammer.boardgame.object.field.Field}. On this connection there can be
 * multiple {@link at.ahammer.boardgame.object.field.FieldConnectionObject}s which are the behavior of the connection.
 */
public abstract class FieldConnection extends GameObject {

    private final Field leftHand;

    private final Field rightHand;

    private final Set<FieldConnectionObject> fieldConnectionObjects = new HashSet<>();

    /**
     * Create a new {@link at.ahammer.boardgame.object.field.FieldConnection}
     *
     * @param id        the id of the {@link at.ahammer.boardgame.object.field.FieldConnection}
     * @param leftHand  the {@link at.ahammer.boardgame.artifact.Artifact} of the left hand
     * @param rightHand the {@link at.ahammer.boardgame.artifact.Artifact} of the right hand
     */
    public FieldConnection(String id, Field leftHand, Field rightHand) {
        super(id);
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    /**
     * Check if the current {@link at.ahammer.boardgame.object.field.FieldConnection} connects the two assigned {@link
     * at.ahammer.boardgame.object.field.Field}s
     *
     * @param leftHand  one {@link at.ahammer.boardgame.object.field.Field}
     * @param rightHand another {@link at.ahammer.boardgame.object.field.Field}
     * @return {@code true} if the {@link at.ahammer.boardgame.object.field.FieldConnection} connects the 2 assigned
     * {@link at.ahammer.boardgame.object.field.Field}s
     */
    public abstract boolean connects(Field leftHand, Field rightHand);

    public abstract Set<FieldConnectionObject> getObjectsOnConnection();

    public abstract void addObjectOnConnection(FieldConnectionObject fieldConnectionObject);

    public abstract void clearObjectsOnConnection();

    public abstract Field getRightHand();

    public abstract Field getLeftHand();

    /**
     * A {@link at.ahammer.boardgame.object.field.FieldConnection} is never visible, because it's a virtual construct.
     *
     * @return {@code false}
     */
    @Override
    public boolean isVisible() {
        return false;
    }
}
