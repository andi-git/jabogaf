package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

import java.util.Set;

/**
 * Get all {@link at.ahammer.boardgame.api.object.GameObject}s that are located on a {@link
 * at.ahammer.boardgame.api.board.field.Field}.
 */
@FunctionalInterface
public interface FunctionGetAllGameObjectsOf {

    /**
     * Get all {@link at.ahammer.boardgame.api.object.GameObject}s that are located on a {@link
     * at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param fieldConnections all available {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param field            the {@link at.ahammer.boardgame.api.board.field.Field} to get all {@link
     *                         at.ahammer.boardgame.api.object.GameObject}s from
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.api.object.GameObject}s that are located on a {@link
     * at.ahammer.boardgame.api.board.field.Field}
     */
    Set<FieldConnectionObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field field);
}
