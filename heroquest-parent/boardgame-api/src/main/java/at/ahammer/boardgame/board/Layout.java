package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.GameContextBean;
import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;

import java.util.HashSet;
import java.util.Set;

/**
 * All the {@link at.ahammer.boardgame.object.field.Field}s of a board are arranged by a concrete layout. So the layout
 * defines the available {@link at.ahammer.boardgame.object.field.Field}s, how the {@link
 * at.ahammer.boardgame.object.field.Field}s are connected via {@link at.ahammer.boardgame.object.field.FieldConnection}s
 * and grouped via {@link at.ahammer.boardgame.board.FieldGroup}.
 */
public abstract class Layout extends GameContextBean {

    private final Set<Field> fields;

    private final Set<FieldConnection> fieldConnections;

    private final Set<FieldGroup> fieldGroups;

    /**
     * Create a new {@link at.ahammer.boardgame.board.Layout}.
     *
     * @param id               the id of the {@link at.ahammer.boardgame.board.Layout}
     * @param fields           all {@link at.ahammer.boardgame.object.field.Field}s of the layout
     * @param fieldConnections all connections of the {@link at.ahammer.boardgame.object.field.Field}s as {@link
     *                         at.ahammer.boardgame.object.field.FieldConnection}s
     * @param fieldGroups      all groups of the {@link at.ahammer.boardgame.object.field.Field}s as {@link
     *                         at.ahammer.boardgame.board.FieldGroup}s
     */
    protected Layout(String id, Set<Field> fields, Set<FieldConnection> fieldConnections, Set<FieldGroup> fieldGroups) {
        super(id);
        if (fields == null || fieldConnections == null || fieldGroups == null) {
            throw new IllegalArgumentException("fields,  fieldConnections and fieldGroups must not be null!");
        }
        this.fields = fields;
        this.fieldConnections = fieldConnections;
        this.fieldGroups = fieldGroups;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public Set<FieldConnection> getFieldConnections() {
        return fieldConnections;
    }

    public Set<FieldGroup> getFieldGroups() {
        return fieldGroups;
    }

    /**
     * Check if the assigned {@link at.ahammer.boardgame.object.field.Field}s are connected.
     *
     * @param leftHand  one {@link at.ahammer.boardgame.object.field.Field}
     * @param rightHand another {@link at.ahammer.boardgame.object.field.Field}
     * @return {@code true} if the {@link at.ahammer.boardgame.object.field.Field}s are connected
     */
    public abstract boolean isConnected(Field leftHand, Field rightHand);

    /**
     * Get the {@link at.ahammer.boardgame.object.field.FieldConnection} between two {@link
     * at.ahammer.boardgame.object.field.Field}s or {@link at.ahammer.boardgame.object.field.FieldConnectionNull} if
     * there is no connection.
     *
     * @param leftHand  one {@link at.ahammer.boardgame.object.field.Field}
     * @param rightHand another {@link at.ahammer.boardgame.object.field.Field}
     * @return the {@link at.ahammer.boardgame.object.field.FieldConnection}s of the 2 {@link
     * at.ahammer.boardgame.object.field.Field}s or a {@link at.ahammer.boardgame.object.field.FieldConnectionNull}
     */
    public abstract FieldConnection getConnection(Field leftHand, Field rightHand);

    /**
     * Get all {@link at.ahammer.boardgame.object.field.FieldConnection}s that intercepts the look from one {@link
     * at.ahammer.boardgame.object.field.Field} to another.
     *
     * @param position the position ({@link at.ahammer.boardgame.object.field.Field}) to look from
     * @param target   the {@link at.ahammer.boardgame.object.field.Field} to look to
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.object.field.FieldConnection}s that are between
     * the look from a position {@link at.ahammer.boardgame.object.field.Field} to the target {@link
     * at.ahammer.boardgame.object.field.Field}.
     */
    public abstract Set<FieldConnection> getLookConnections(Field position, Field target);

    /**
     * Get all {@link at.ahammer.boardgame.object.GameObject}s that are located on a {@link
     * at.ahammer.boardgame.object.field.Field}.
     *
     * @param field the {@link at.ahammer.boardgame.object.field.Field} to get all {@link
     *              at.ahammer.boardgame.object.GameObject}s from
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.object.GameObject}s that are located on a {@link
     * at.ahammer.boardgame.object.field.Field}
     */
    public abstract Set<GameObject> getAllGameObjectsOf(Field field);
}
