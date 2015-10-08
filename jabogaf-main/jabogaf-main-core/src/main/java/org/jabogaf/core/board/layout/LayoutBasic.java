package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.FunctionGetAllGameObjectsOf;
import org.jabogaf.api.board.layout.FunctionGetConnection;
import org.jabogaf.api.board.layout.FunctionIsConnected;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.core.util.CacheFor1Field;
import org.jabogaf.core.util.CacheFor2Fields;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All the {@link org.jabogaf.api.board.field.Field}s of a board are arranged by a concrete layout. So the layout
 * defines the available {@link org.jabogaf.api.board.field.Field}s, how the {@link org.jabogaf.api.board.field.Field}s
 * are connected via {@link org.jabogaf.api.board.field.FieldConnection}s and grouped via {@link
 * org.jabogaf.api.board.field.FieldGroup}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class LayoutBasic extends GameContextBeanBasic implements Layout {

    private final Set<Field> fields;

    private final Set<FieldConnection> fieldConnections;

    private final Set<FieldGroup> fieldGroups;

    private final CacheFor2Fields<Boolean> isConnectedCache = new CacheFor2Fields<>();

    private final CacheFor2Fields<FieldConnection> getConnectionCache = new CacheFor2Fields<>();

    private final CacheFor1Field<Set<FieldGroup>> getFieldsGroupsForCache = new CacheFor1Field<>();

    private final CacheFor1Field<Set<FieldConnection>> getFieldsConnectionsCache = new CacheFor1Field<>();

    @Inject
    private FunctionIsConnected functionIsConnected;

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Inject
    private FunctionGetAllGameObjectsOf layoutFunctionGetAllGameObjectsOf;

    /**
     * Create a new {@link org.jabogaf.core.board.layout.LayoutBasic}.
     *
     * @param id               the id of the {@link org.jabogaf.core.board.layout.LayoutBasic}
     * @param fields           all {@link org.jabogaf.api.board.field.Field}s of the layout
     * @param fieldConnections all connections of the {@link org.jabogaf.api.board.field.Field}s as {@link
     *                         org.jabogaf.api.board.field.FieldConnection}s
     * @param fieldGroups      all groups of the {@link org.jabogaf.api.board.field.Field}s as {@link
     *                         org.jabogaf.api.board.field.FieldGroup}s
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
        return isConnectedCache.get(source, target, () -> functionIsConnected.isConnected(fieldConnections, source, target));
    }

    @Override
    public FieldConnection getConnection(Field source, Field target) {
        return getConnectionCache.get(source, target, () -> functionGetConnection.getConnection(fieldConnections, source, target));
    }

    @Override
    public abstract Set<FieldConnection> getLookConnections(Field position, Field target);

    @Override
    public Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand) {
        // no cache here because FieldConnectionObjects can change at runtime
        return layoutFunctionGetAllGameObjectsOf.getAllGameObjectsOf(fieldConnections, leftHand, rightHand);
    }

    @Override
    public Set<FieldGroup> getFieldsGroupsFor(Field field) {
        return getFieldsGroupsForCache.get(field, () -> fieldGroups.stream().filter(fg -> fg.contains(field)).collect(Collectors.toSet()));
    }

    @Override
    public Set<Field> getConnectedFields(Field field) {
        return getFieldConnections(field).stream().map(fc -> {
            if (fc.getLeftHand().equals(field)) {
                return fc.getRightHand();
            } else {
                return fc.getLeftHand();
            }
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<FieldConnection> getFieldConnections(Field field) {
        return getFieldsConnectionsCache.get(field, () -> fieldConnections.stream().filter(fc -> fc.contains(field)).collect(Collectors.toSet()));
    }
}
