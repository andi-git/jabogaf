package org.jabogaf.api.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;

import java.util.Set;

/**
 * Get the {@link FieldConnection} between two {@link Field}s
 */
@FunctionalInterface
public interface FunctionGetConnection {

    /**
     * Get the {@link FieldConnection} between two {@link Field}s or the null-implementation if
     * there is no connection.
     *
     * @param fieldConnections all available {@link FieldConnection}s
     * @param source           one {@link Field}
     * @param target           another {@link Field}
     * @return the {@link FieldConnection}s of the 2 {@link Field}s or a null-implementation
     */
    FieldConnection getConnection(Set<FieldConnection> fieldConnections, Field source, Field target);
}
