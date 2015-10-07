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

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
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

    private final Map<SourceTargetKey, Boolean> isConnectedCache = new HashMap<>();

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
        SourceTargetKey sourceTargetKey = new SourceTargetKey(source, target);
        if (!isConnectedCache.containsKey(sourceTargetKey)) {
            isConnectedCache.put(sourceTargetKey, functionIsConnected.isConnected(fieldConnections, source, target));
        }
        return isConnectedCache.get(sourceTargetKey);
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
        return fieldConnections.stream().filter(fc -> fc.contains(field)).collect(Collectors.toSet());
    }

    private static class SourceTargetKey {

        private final Field source;

        private final Field target;

        public SourceTargetKey(Field source, Field target) {
            this.source = source;
            this.target = target;
        }

        public Field getSource() {
            return source;
        }

        public Field getTarget() {
            return target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SourceTargetKey that = (SourceTargetKey) o;
            return source.equals(that.source) && target.equals(that.target);
        }

        @Override
        public int hashCode() {
            int result = source.hashCode();
            result = 31 * result + target.hashCode();
            return result;
        }
    }
}
