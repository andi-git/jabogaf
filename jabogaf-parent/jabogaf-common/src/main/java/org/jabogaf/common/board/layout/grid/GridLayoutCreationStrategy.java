package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.core.board.field.FieldConnectionNull;
import org.jabogaf.core.board.field.FieldNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The strategy to create a {@link GridLayout}.
 */
public abstract class GridLayoutCreationStrategy {

    protected final Field[][] fieldsArray;

    protected final Map<String, Field> fields;

    protected final Map<String, FieldConnection> fieldConnections;

    protected final Map<String, FieldGroup> fieldGroups;

    public GridLayoutCreationStrategy(int maxX, int maxY) {
        fieldsArray = new Field[maxY][maxX];
        fields = new HashMap<>();
        fieldConnections = new HashMap<>();
        fieldGroups = new HashMap<>();

        createFields(maxX, maxY);
        createFieldConnections();
        createFieldConnectionObjects();
        createFieldGroups();
    }

    protected final void createFields(int maxX, int maxY) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String id = Field.class.getSimpleName() + ":" + x + "," + y;
                fieldsArray[y][x] = createDefaultField(id);
                fields.put(id, fieldsArray[y][x]);
            }
        }
    }

    protected final void createFieldConnections() {
        for (int y = 0; y < fieldsArray.length; y++) {
            for (int x = 0; x < fieldsArray[y].length; x++) {
                if (y != fieldsArray.length - 1) {
                    String id = FieldConnection.class.getSimpleName() + ":" + x + "," + y + "-" + x + "," + (y + 1);
                    FieldConnection fieldConnection = createDefaultFieldConnection(id, fieldsArray[y][x], fieldsArray[y + 1][x]);
                    fieldConnections.put(id, fieldConnection);
                }
                if (x != fieldsArray[y].length - 1) {
                    String id = FieldConnection.class.getSimpleName() + ":" + x + "," + y + "-" + (x + 1) + "," + y;
                    FieldConnection fieldConnection = createDefaultFieldConnection(id, fieldsArray[y][x], fieldsArray[y][x + 1]);
                    fieldConnections.put(id, fieldConnection);
                }
            }
        }
    }

    protected void createFieldConnectionObjects() {

    }

    protected void createFieldGroups() {

    }

    protected Field createDefaultField(String id) {
        return new FieldNull();
    }

    protected FieldConnection createDefaultFieldConnection(String id, Field field1, Field field2) {
        return new FieldConnectionNull();
    }

    public final Field[][] getFieldsArray() {
        Field[][] result = new Field[fieldsArray.length][fieldsArray[0].length];
        for (int y = 0; y < fieldsArray.length; y++) {
            System.arraycopy(fieldsArray[y], 0, result[y], 0, fieldsArray[0].length);
        }
        return result;
    }

    public final Set<Field> getFields() {
        return new HashSet<>(fields.values());
    }

    public final Set<FieldConnection> getFieldConnections() {
        return new HashSet<>(fieldConnections.values());
    }

    public final Set<FieldGroup> getFieldGroups() {
        return new HashSet<>(fieldGroups.values());
    }

    public final Set<Field> getAllFieldsInRectangle(int fromX, int fromY, int toX, int toY) {
        Set<Field> fieldsInRectangle = new HashSet<>();
        for (int x = fromX; x <= toX; x++) {
            for (int y = fromY; y <= toY; y++) {
                fieldsInRectangle.add(fieldsArray[y][x]);
            }
        }
        return fieldsInRectangle;
    }
}
