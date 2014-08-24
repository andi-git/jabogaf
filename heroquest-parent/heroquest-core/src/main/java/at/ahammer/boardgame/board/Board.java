package at.ahammer.boardgame.board;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;

import java.util.Set;

/**
 * Created by andreas on 8/23/14.
 */
public class Board extends NewInstanceInGameContext {

    private final Set<Field> fields;

    private final Set<FieldConnection> fieldConnections;

    public Board(String id, Set<Field> fields, Set<FieldConnection> fieldConnections) {
        super(id);
        if (fields == null || fieldConnections == null) {
            throw new IllegalArgumentException("fields and fieldConnections must not be null!");
        }
        this.fields = fields;
        this.fieldConnections = fieldConnections;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public Set<FieldConnection> getFieldConnections() {
        return fieldConnections;
    }

    public boolean isConnected(Field field1, Field field2) {
        return fieldConnections.stream().anyMatch(fc -> fc.connect(field1, field2));
    }

    public FieldConnection getConnection(Field field1, Field field2) {
        return fieldConnections.stream().filter(fc -> fc.connect(field1, field2)).findFirst().get();
    }
}
