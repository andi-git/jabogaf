package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.Set;

/**
 * All the {@link at.ahammer.boardgame.api.board.field.Field}s of a board are arranged by a concrete layout. So the layout
 * defines the available {@link at.ahammer.boardgame.api.board.field.Field}s, how the {@link
 * at.ahammer.boardgame.api.board.field.Field}s are connected via {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
 * and grouped via {@link at.ahammer.boardgame.api.board.field.FieldGroup}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Typed()
public abstract class Layout extends GameContextBean {

    private final Set<Field> fields;

    private final Set<FieldConnection> fieldConnections;

    private final Set<FieldGroup> fieldGroups;

    @Inject
    private FunctionIsConnected functionIsConnected;

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Inject
    private FunctionGetAllGameObjectsOf layoutFunctionGetAllGameObjectsOf;

    /**
     * Create a new {@link Layout}.
     *
     * @param id               the id of the {@link Layout}
     * @param fields           all {@link at.ahammer.boardgame.api.board.field.Field}s of the layout
     * @param fieldConnections all connections of the {@link at.ahammer.boardgame.api.board.field.Field}s as {@link
     *                         at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param fieldGroups      all groups of the {@link at.ahammer.boardgame.api.board.field.Field}s as {@link
     *                         at.ahammer.boardgame.api.board.field.FieldGroup}s
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
     * @see FunctionIsConnected#isConnected(java.util.Set, at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.board.field.Field)
     */
    public boolean isConnected(Field source, Field target) {
        return functionIsConnected.isConnected(fieldConnections, source, target);
    }

    /**
     * @see FunctionGetConnection#getConnection(java.util.Set, at.ahammer.boardgame.api.board.field.Field, at.ahammer.boardgame.api.board.field.Field)
     */
    public FieldConnection getConnection(Field source, Field target) {
        return functionGetConnection.getConnection(fieldConnections, source, target);
    }

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
    public abstract Set<FieldConnection> getLookConnections(Field position, Field target);

    /**
     * @see FunctionGetAllGameObjectsOf#getAllGameObjectsOf(java.util.Set, at.ahammer.boardgame.api.board.field.Field)
     */
    public Set<FieldConnectionObject> getAllGameObjectsOf(Field field) {
        return layoutFunctionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, field);
    }
}
