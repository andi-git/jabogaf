package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.board.layout.FunctionGetAllGameObjectsOf;
import at.ahammer.boardgame.api.board.layout.FunctionGetConnection;
import at.ahammer.boardgame.api.board.layout.FunctionIsConnected;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All the {@link at.ahammer.boardgame.api.board.field.Field}s of a board are arranged by a concrete layout. So the
 * layout defines the available {@link at.ahammer.boardgame.api.board.field.Field}s, how the {@link
 * at.ahammer.boardgame.api.board.field.Field}s are connected via {@link at.ahammer.boardgame.api.board.field.FieldConnection}s
 * and grouped via {@link at.ahammer.boardgame.api.board.field.FieldGroup}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class LayoutBasic extends GameContextBeanBasic implements Layout {

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
     * Create a new {@link at.ahammer.boardgame.core.board.layout.LayoutBasic}.
     *
     * @param id               the id of the {@link at.ahammer.boardgame.core.board.layout.LayoutBasic}
     * @param fields           all {@link at.ahammer.boardgame.api.board.field.Field}s of the layout
     * @param fieldConnections all connections of the {@link at.ahammer.boardgame.api.board.field.Field}s as {@link
     *                         at.ahammer.boardgame.api.board.field.FieldConnection}s
     * @param fieldGroups      all groups of the {@link at.ahammer.boardgame.api.board.field.Field}s as {@link
     *                         at.ahammer.boardgame.api.board.field.FieldGroup}s
     */
    protected LayoutBasic(String id, Set<Field> fields, Set<FieldConnection> fieldConnections, Set<FieldGroup> fieldGroups) {
        super(id);
        if (fields == null || fieldConnections == null || fieldGroups == null) {
            throw new IllegalArgumentException("fields,  fieldConnections and fieldGroups must not be null!");
        }
        this.fields = fields;
        this.fieldConnections = fieldConnections;
        this.fieldGroups = fieldGroups;
    }

    @Override
    public Set<Field> getFields() {
        return fields;
    }

    @Override
    public Stream<Field> getFieldsAsStream() {
        return getFields().stream();
    }

    @Override
    public Set<FieldConnection> getFieldConnections() {
        return fieldConnections;
    }

    @Override
    public Set<FieldGroup> getFieldGroups() {
        return fieldGroups;
    }

    @Override
    public boolean isConnected(Field source, Field target) {
        return functionIsConnected.isConnected(fieldConnections, source, target);
    }

    @Override
    public FieldConnection getConnection(Field source, Field target) {
        return functionGetConnection.getConnection(fieldConnections, source, target);
    }

    @Override
    public abstract Set<FieldConnection> getLookConnections(Field position, Field target);

    @Override
    public Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand) {
        return layoutFunctionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, leftHand, rightHand);
    }

    @Override
    public Set<FieldGroup> getFieldsGroupsFor(Field field) {
        return fieldGroups.stream().filter(fg -> fg.contains(field)).collect(Collectors.toSet());
    }
}
