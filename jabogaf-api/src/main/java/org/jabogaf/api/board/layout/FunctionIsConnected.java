package org.jabogaf.api.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;

import java.util.Set;

/**
 * Check if two {@link Field}s are connected.
 */
@FunctionalInterface
public interface FunctionIsConnected {

    /**
     * Check if the assigned {@link Field}s are connected.
     *
     * @param fieldConnections all available {@link FieldConnection}s
     * @param source           one {@link Field}
     * @param target           another {@link Field}
     * @return {@code true} if the {@link Field}s are connected
     */
    boolean isConnected(Set<FieldConnection> fieldConnections, Field source, Field target);
}
