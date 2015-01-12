package at.ahammer.boardgame.common.board.layout;

import at.ahammer.boardgame.api.board.field.*;
import at.ahammer.boardgame.core.board.field.FieldConnectionNull;
import at.ahammer.boardgame.core.board.field.FieldNull;

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

    public GridLayoutCreationStrategy(int x, int y) {
        fieldsArray = new Field[y][x];
        fields = new HashMap<>();
        fieldConnections = new HashMap<>();
        fieldGroups = new HashMap<>();

        createFields(x, y);
        createFieldConnections(x, y);
        createFieldConnectionObjects();
        createFieldGroups();
    }

    protected final void createFields(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                String id = Field.class.getSimpleName() + ":" + i + "," + j;
                fieldsArray[i][j] = createDefaultField(id);
                fields.put(id, fieldsArray[i][j]);
            }
        }
    }

    protected final void createFieldConnections(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ((i + 1) < y) {
                    String id = FieldConnection.class.getSimpleName() + ":" + i + "," + j + "-" + (i + 1) + "," + j;
                    fieldConnections.put(id, createDefaultFieldConnection(id, fieldsArray[i][j], fieldsArray[i + 1][j]));
                }
                if ((j + 1) < x) {
                    String id = FieldConnection.class.getSimpleName() + ":" + i + "," + j + "-" + i + "," + (j + 1);
                    fieldConnections.put(id, createDefaultFieldConnection(id, fieldsArray[i][j], fieldsArray[i][j + 1]));
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
        for (int i = 0; i < fieldsArray.length; i++) {
            for (int j = 0; j < fieldsArray[0].length; j++) {
                result[i][j] = fieldsArray[i][j];
            }
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
        for (int i = fromX; i <= toX; i++) {
            for (int j = fromY; j <= toY; j++) {
                fieldsInRectangle.add(fieldsArray[i][j]);
            }
        }
        return fieldsInRectangle;
    }
}
