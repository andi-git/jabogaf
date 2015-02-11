package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.subject.GameSubject;

import java.util.List;
import java.util.Set;

/**
 * A basic class for a field in the game, which represents a unit where a {@link at.ahammer.boardgame.api.subject.GameSubject}
 * can be positioned.
 * <p/>
 * A field is a {@link at.ahammer.boardgame.api.object.GameObject} and is always visible.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public interface Field extends GameContextBean {

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
     * Get a {@link java.util.List} of all available {@link at.ahammer.boardgame.api.subject.GameSubject}s on the
     * current {@link at.ahammer.boardgame.api.board.field.Field}. The {@link at.ahammer.boardgame.api.subject.GameSubject}s
     * are ordered by natural order.
     *
     * @return a {@link java.util.List} of {@link at.ahammer.boardgame.api.subject.GameSubject}s available on the
     * current {@link at.ahammer.boardgame.api.board.field.Field} sorted by natural order.
     */
    List<GameSubject> getGameSubjects();

    /**
     * Get a natural sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s where
     * the current {@link at.ahammer.boardgame.api.board.field.Field} is located.
     *
     * @return a natural sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s
     * where the current {@link at.ahammer.boardgame.api.board.field.Field} is located
     */
    List<FieldGroup> getFieldsGroups();

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.FieldConnection}s where the {@link
     * at.ahammer.boardgame.api.board.field.Field} is included.
     *
     * @return all {@link at.ahammer.boardgame.api.board.field.FieldConnection}s where the {@link
     * at.ahammer.boardgame.api.board.field.Field} is included
     */
    Set<FieldConnection> getFieldConnections();

    /**
     * Get all connected {@link at.ahammer.boardgame.api.board.field.Field}s.
     *
     * @return all connected {@link at.ahammer.boardgame.api.board.field.Field}s
     */
    Set<Field> getConnectedFields();

    Resource movementCost();
}
