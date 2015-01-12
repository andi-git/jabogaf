package at.ahammer.boardgame.api.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import java.util.Set;

/**
 * Check if two {@link at.ahammer.boardgame.api.board.field.Field}s are connected.
 */
@FunctionalInterface
public interface FunctionIsConnected {

    /**
     * Check if the assigned {@link at.ahammer.boardgame.api.board.field.Field}s are connected.
     *
     * @param fieldConnections all available {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param source           one {@link at.ahammer.boardgame.api.board.field.Field}
     * @param target           another {@link at.ahammer.boardgame.api.board.field.Field}
     * @return {@code true} if the {@link at.ahammer.boardgame.api.board.field.Field}s are connected
     */
    boolean isConnected(Set<FieldConnection> fieldConnections, Field source, Field target);
}
