package at.ahammer.boardgame.api.board.layout;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LookReport {

    private final Set<Field> fields = new HashSet<>();

    private final Set<FieldConnection> fieldConnections = new HashSet<>();

    public Set<Field> getFields() {
        return Collections.unmodifiableSet(fields);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void clearFields() {
        fields.clear();
    }

    public Set<FieldConnection> getFieldConnections() {
        return Collections.unmodifiableSet(fieldConnections);
    }

    public void addFieldConnection(FieldConnection fieldConnection) {
        fieldConnections.add(fieldConnection);
    }

    public void clearFieldConnections() {
        fieldConnections.clear();
    }
}