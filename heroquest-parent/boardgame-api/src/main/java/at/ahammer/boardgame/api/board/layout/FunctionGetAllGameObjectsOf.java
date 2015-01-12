package at.ahammer.boardgame.api.board.layout;

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
     * Get all {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}s that are located on a {@link
     * at.ahammer.boardgame.api.board.field.FieldConnection} where the {@link at.ahammer.boardgame.api.board.field.Field}
     * is involved.
     *
     * @param fieldConnections all available {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param leftHand         the one {@link at.ahammer.boardgame.api.board.field.Field} of the {@link
     *                         at.ahammer.boardgame.api.board.field.FieldConnection}
     * @param rightHand        the other {@link at.ahammer.boardgame.api.board.field.Field} of the {@link
     *                         at.ahammer.boardgame.api.board.field.FieldConnection}
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}s that are
     * located on a {@link at.ahammer.boardgame.api.board.field.FieldConnection}
     */
    Set<FieldConnectionObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field leftHand, Field rightHand);
}
