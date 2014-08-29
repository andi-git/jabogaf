package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldGroup;

import java.util.Set;

/**
 * Created by andreas on 8/24/14.
 */
public abstract class Layout extends NewInstanceInGameContext {

    private final Set<Field> fields;

    private final Set<FieldConnection> fieldConnections;

    private final Set<FieldGroup> fieldGroups;

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

    public boolean isConnected(Field field1, Field field2) {
        return fieldConnections.stream().anyMatch(fc -> fc.connect(field1, field2));
    }

    public FieldConnection getConnection(Field field1, Field field2) {
        return fieldConnections.stream().filter(fc -> fc.connect(field1, field2)).findFirst().get();
    }

    public abstract Set<FieldConnection> getLookConnections(Field field1, Field field2);
}
