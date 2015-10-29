package org.jabogaf.api.board.field;

import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.GameSubject;

import java.util.List;
import java.util.Set;

/**
 * A basic class for a field in the game, which represents a unit where a {@link GameSubject} can be positioned.
 * <p/>
 * A field is a {@link GameObject} and is always visible.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public interface Field extends GameContextBean<Field>, ContainsGameObjects, ContainsGameSubjects {

    /**
     * Check if the {@link Field} is connected to another {@link Field}
     *
     * @param target the {@link Field} to check if the current {@link Field} is conected to
     * @return true if both {@link Field}s are connected
     */
    boolean isConnected(Field target);

    /**
     * Get the representation of the connection between the two {@link Field}s as {@link FieldConnection}.
     * <p/>
     * If the {@link Field}s are not connected, the null-implementation of {@link FieldConnection} will be returned, not
     * null.
     *
     * @param target the other {@link Field}
     * @return the representation of the connection as {@link FieldConnection}
     */
    FieldConnection getConnectionTo(Field target);

    /**
     * Get all {@link FieldConnection}s of the current {@link Field}.
     *
     * @return all {@link FieldConnection}s of the current {@link Field}
     */
    Set<FieldConnection> getFieldConnections();

    /**
     * Get a natural sorted {@link List} of all {@link FieldGroup}s where the current {@link Field} is located.
     *
     * @return a natural sorted {@link List} of all {@link FieldGroup}s where the current {@link Field} is located
     */
    List<FieldGroup> getFieldsGroups();

    /**
     * Get all connected {@link Field}s.
     *
     * @return all connected {@link Field}s
     */
    Set<Field> getConnectedFields();

    Resource movementCost();

    void setMovementCost(Resource resource);
}
