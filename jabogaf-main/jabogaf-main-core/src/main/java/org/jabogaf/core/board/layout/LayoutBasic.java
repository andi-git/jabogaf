package org.jabogaf.core.board.layout;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.*;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.common.board.layout.KeyTwoFieldsBasic;
import org.jabogaf.common.board.layout.grid.GridLayoutCreationStrategy;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.core.util.CacheFor1Field;
import org.jabogaf.core.util.CacheFor2Fields;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * All the {@link Field}s of a board are arranged by a concrete layout. So the layout defines the available {@link
 * Field}s, how the {@link Field}s are connected via {@link FieldConnection}s and grouped via {@link FieldGroup}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class LayoutBasic extends GameContextBeanBasic<Layout> implements Layout {

    private final Set<Field> fields = new HashSet<>();

    private final Set<FieldConnection> fieldConnections = new HashSet<>();

    private final Set<FieldGroup> fieldGroups = new HashSet<>();

    private final Map<KeyTwoFields, LookPath> lookPaths = new HashMap<>();

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
     * Create a new {@link LayoutBasic}.
     *
     * @param id                          the id of the {@link LayoutBasic}
     * @param fields                      all {@link Field}s of the layout
     * @param fieldConnections            all connections of the {@link Field}s as {@link FieldConnection}s
     * @param fieldGroups                 all groups of the {@link Field}s as {@link FieldGroup}s
     */
    protected LayoutBasic(String id, Set<Field> fields, Set<FieldConnection> fieldConnections, Set<FieldGroup> fieldGroups, Map<KeyTwoFields, LookPath> lookPaths) {
        super(id);
        if (fields == null || fieldConnections == null || fieldGroups == null) {
            throw new IllegalArgumentException("fields,  fieldConnections and fieldGroups must not be null!");
        }
        checkNotNull(fields, "fields must not be null");
        checkNotNull(fieldConnections, "fieldConnections must not be null");
        checkNotNull(fieldGroups, "fieldGroups must not be null");
        checkNotNull(lookPaths, "lookPaths must not be null");
        checkArgument(!fields.isEmpty(), "fields must not be empty");
        checkArgument(!fieldConnections.isEmpty(), "fieldConnections must not be empty");
        checkArgument(!lookPaths.isEmpty(), "lookPaths must not be empty");
        this.fields.addAll(fields);
        this.fieldConnections.addAll(fieldConnections);
        this.fieldGroups.addAll(fieldGroups);
        this.lookPaths.putAll(lookPaths);
    }

    /**
     * Create a new {@link LayoutBasic}
     *
     * @param id                          the id of the {@link LayoutBasic}
     * @param gridLayoutCreationStrategy  the stategy to create the layout
     */
    public LayoutBasic(String id, GridLayoutCreationStrategy gridLayoutCreationStrategy) {
        this(id, gridLayoutCreationStrategy.getFields(), gridLayoutCreationStrategy.getFieldConnections(), gridLayoutCreationStrategy.getFieldGroups(), gridLayoutCreationStrategy.getLookPaths());
    }

    @Override
    public void initAfterBoard() {
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
    public Optional<FieldConnection> getConnection(Field source, Field target) {
        return Optional.of(getConnectionCache.get(source, target, () -> functionGetConnection.getConnection(fieldConnections, source, target)));
    }

    @Override
    public List<LayoutActionImpact<?, ?>> getAllLayoutActionImpacts(Field fieldFrom, Field fieldTo) {
        List<LayoutActionImpact<?, ?>> layoutActionImpacts = new ArrayList<>();
        Optional<LookPath> lookPath = getLookPath(fieldFrom, fieldTo);
        if (lookPath.isPresent()) {
            layoutActionImpacts.addAll(lookPath.get().getLayoutActionImpacts());
        }
        return Collections.unmodifiableList(layoutActionImpacts);
    }

    @Override
    public Set<GameObject> getAllGameObjectsOnFieldConnection(Field leftHand, Field rightHand) {
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
    public Optional<LookPath> getLookPath(Field fieldFrom, Field fieldTo) {
        return Optional.ofNullable(lookPaths.get(new KeyTwoFieldsBasic(fieldFrom, fieldTo)));
    }

    @Override
    public Map<KeyTwoFields, LookPath> getLookPaths() {
        return Collections.unmodifiableMap(lookPaths);
    }

    @Override
    public Set<FieldConnection> getFieldConnections(Field field) {
        return getFieldsConnectionsCache.get(field, () -> fieldConnections.stream().filter(fc -> fc.contains(field)).collect(Collectors.toSet()));
    }
}
