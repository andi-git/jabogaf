package org.jabogaf.api.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;

import java.util.Set;

/**
 * Get all {@link FieldConnectionObject}s that are located on a {@link Field}.
 */
@FunctionalInterface
public interface FunctionGetAllGameObjectsOf {

    /**
     * Get all {@link FieldConnectionObject}s that are located on a {@link FieldConnection} where the {@link Field}
     * is involved.
     *
     * @param fieldConnections all available {@link FieldConnection}s
     * @param leftHand         the one {@link Field} of the {@link FieldConnection}
     * @param rightHand        the other {@link Field} of the {@link FieldConnection}
     * @return a {@link java.util.Set} of {@link FieldConnectionObject}s that are located on a {@link FieldConnection}
     */
    Set<FieldConnectionObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field leftHand, Field rightHand);
}
