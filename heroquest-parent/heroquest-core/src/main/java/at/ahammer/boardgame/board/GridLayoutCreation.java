package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.field.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by andreas on 8/24/14.
 */
public class GridLayoutCreation {

    protected final Field[][] fieldsArray;

    protected final Map<String, Field> fields;

    protected final Map<String, FieldConnection> fieldConnections;

    protected final Map<String, FieldGroup> fieldGroups;

    public GridLayoutCreation(int rows, int cols) {
        fieldsArray = new Field[rows][cols];
        fields = new HashMap<>();
        fieldConnections = new HashMap<>();
        fieldGroups = new HashMap<>();

        createFields(rows, cols);
        createFieldConnections(rows, cols);
        createFieldConnectionObjects();
        createFieldGroups();
    }

    protected void createFields(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String id = Field.class.getSimpleName() + ":" + i + "," + j;
                fieldsArray[i][j] = createDefaultField(id);
                fields.put(id, fieldsArray[i][j]);
            }
        }
    }

    protected void createFieldConnections(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i + 1) < rows) {
                    String id = FieldConnection.class.getSimpleName() + ":" + i + "," + j + "-" + (i + 1) + "," + j;
                    fieldConnections.put(id, createDefaultFieldConnection(id, fieldsArray[i][j], fieldsArray[i + 1][j]));
                }
                if ((j + 1) < cols) {
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
        return new FieldEmpty(id);
    }

    protected FieldConnection createDefaultFieldConnection(String id, Field field1, Field field2) {
        return new FieldConnectionEmpty(id, field1, field2);
    }

    public Field[][] getFieldsArray() {
        Field[][] result = new Field[fieldsArray.length][fieldsArray[0].length];
        for (int i = 0; i < fieldsArray.length; i++) {
            for (int j = 0; j < fieldsArray[0].length; j++) {
                result[i][j] = fieldsArray[i][j];
            }
        }
        return result;
    }

    public Set<Field> getFields() {
        return new HashSet<>(fields.values());
    }

    public Set<FieldConnection> getFieldConnections() {
        return new HashSet<>(fieldConnections.values());
    }

    public Set<FieldGroup> getFieldGroups() {
        return new HashSet<>(fieldGroups.values());
    }

    public Set<Field> getAllFieldsInRectangle(int startRow, int startCol, int endRow, int endCol) {
        Set<Field> fieldsInRectangle = new HashSet<>();
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                fieldsInRectangle.add(fieldsArray[i][j]);
            }
        }
        return fieldsInRectangle;
    }
}
