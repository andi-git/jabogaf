package at.ahammer.boardgame.api.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import java.util.Set;
import java.util.stream.Stream;

/**
 * All the {@link at.ahammer.boardgame.api.board.field.Field}s of a board are arranged by a concrete layout. So the
 * layout defines the available {@link at.ahammer.boardgame.api.board.field.Field}s, how the {@link
 * at.ahammer.boardgame.api.board.field.Field}s are connected via {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
 * and grouped via {@link at.ahammer.boardgame.api.board.field.FieldGroup}.
 */
public interface Layout extends GameContextBean {

    Set<Field> getFields();

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.Field}s as {@link java.util.stream.Stream}.
     *
     * @return the {@link at.ahammer.boardgame.api.board.field.Field}s as {@link java.util.stream.Stream}
     */
    Stream<Field> getFieldsAsStream();

    Set<FieldConnection> getFieldConnections();

    Set<FieldGroup> getFieldGroups();

    /**
     * @see FunctionIsConnected#isConnected(java.util.Set, at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    boolean isConnected(Field source, Field target);

    /**
     * @see FunctionGetConnection#getConnection(java.util.Set, at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    FieldConnection getConnection(Field source, Field target);

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.FieldConnection}s that intercepts the look from one {@link
     * at.ahammer.boardgame.api.board.field.Field} to another.
     *
     * @param position the position ({@link at.ahammer.boardgame.api.board.field.Field}) to look from
     * @param target   the {@link at.ahammer.boardgame.api.board.field.Field} to look to
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.api.board.field.FieldConnection}s that are between
     * the look from a position {@link at.ahammer.boardgame.api.board.field.Field} to the target {@link
     * at.ahammer.boardgame.api.board.field.Field}.
     */
    Set<FieldConnection> getLookConnections(Field position, Field target);

    /**
     * @see FunctionGetAllGameObjectsOf#getAllGameObjectsOf(java.util.Set, at.ahammer.boardgame.api.board.field.Field,
     * at.ahammer.boardgame.api.board.field.Field)
     */
    Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand);

    /**
     * Get a {@link java.util.Set} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s where the assigned
     * {@link at.ahammer.boardgame.api.board.field.Field} is located.
     *
     * @param field the {@link at.ahammer.boardgame.api.board.field.Field} to check
     * @return a {@link java.util.Set} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s where the
     * assigned {@link at.ahammer.boardgame.api.board.field.Field} is located
     */
    Set<FieldGroup> getFieldsGroupsFor(Field field);

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.FieldConnection}s where the {@link
     * at.ahammer.boardgame.api.board.field.Field} is included.
     *
     * @param field the {@link at.ahammer.boardgame.api.board.field.Field} to get all {@link
     *              at.ahammer.boardgame.api.board.field.FieldConnection}s of
     * @return all {@link at.ahammer.boardgame.api.board.field.FieldConnection}s where the {@link
     * at.ahammer.boardgame.api.board.field.Field} is included
     */
    Set<FieldConnection> getFieldConnections(Field field);

    /**
     * Get all connected {@link at.ahammer.boardgame.api.board.field.Field} of an assigned {@link
     * at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param field the {@link at.ahammer.boardgame.api.board.field.Field} to get all connected {@link
     *              at.ahammer.boardgame.api.board.field.Field}s of
     * @return all connected {@link at.ahammer.boardgame.api.board.field.Field} of an assigned {@link
     * at.ahammer.boardgame.api.board.field.Field}
     */
    Set<Field> getConnectedFields(Field field);
}
