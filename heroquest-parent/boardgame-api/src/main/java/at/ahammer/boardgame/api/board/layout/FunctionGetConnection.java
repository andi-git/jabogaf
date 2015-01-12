package at.ahammer.boardgame.api.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import java.util.Set;

/**
 * Get the {@link at.ahammer.boardgame.api.board.field.FieldConnection} between two {@link at.ahammer.boardgame.api.board.field.Field}s
 */
@FunctionalInterface
public interface FunctionGetConnection {

    /**
     * Get the {@link at.ahammer.boardgame.api.board.field.FieldConnection} between two {@link
     * at.ahammer.boardgame.api.board.field.Field}s or {@link at.ahammer.boardgame.api.board.field.FieldConnectionNull} if
     * there is no connection.
     *
     * @param fieldConnections all available {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param source           one {@link at.ahammer.boardgame.api.board.field.Field}
     * @param target           another {@link at.ahammer.boardgame.api.board.field.Field}
     * @return the {@link at.ahammer.boardgame.api.board.field.FieldConnection}s of the 2 {@link
     * at.ahammer.boardgame.api.board.field.Field}s or a {@link at.ahammer.boardgame.api.board.field.FieldConnectionNull}
     */
    FieldConnection getConnection(Set<FieldConnection> fieldConnections, Field source, Field target);
}
