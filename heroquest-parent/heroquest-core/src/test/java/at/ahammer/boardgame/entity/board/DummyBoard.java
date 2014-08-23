package at.ahammer.boardgame.entity.board;

import at.ahammer.boardgame.entity.object.field.Field;
import at.ahammer.boardgame.entity.object.field.FieldConnection;
import at.ahammer.boardgame.entity.object.field.FieldConnectionObject;
import at.ahammer.heroquest.object.field.Door;
import at.ahammer.heroquest.object.field.Wall;

import javax.enterprise.inject.Typed;
import java.util.*;

/**
 * Created by andreas on 8/23/14.
 */
@Typed()
public class DummyBoard extends Board {

    private static final int MAX_ROWS = 5;

    private static final int MAX_COLS = 5;

    private static Field[][] fieldsArray;

    private static Map<String, Field> fields;

    private static Map<String, FieldConnection> fieldConnections;

    private static Map<String, FieldConnectionObject> fieldConnectionObjects;

    public static DummyBoard getNewInstance() {
        init();
        return new DummyBoard();
    }

    private static void init() {
        System.out.println("init DummyBoard");
        fieldsArray = new Field[MAX_ROWS][MAX_COLS];
        fields = new HashMap<>();
        fieldConnections = new HashMap<>();
        fieldConnectionObjects = new HashMap<>();
        // create Fields
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                String id = "field:" + i + "," + j;
                fieldsArray[i][j] = new DummyField(id);
                fields.put(id, fieldsArray[i][j]);
//                System.out.println("    add field: " + fieldsArray[i][j]);
            }
        }
        // create FieldConnections
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                if ((i + 1) < MAX_ROWS) {
                    String id = "fieldConnection:" + i + "," + j + "-" + (i + 1) + "," + j;
                    DummyFieldConnection fieldConnection = new DummyFieldConnection(id, fieldsArray[i][j], fieldsArray[i + 1][j]);
                    fieldConnections.put(id, fieldConnection);
//                    System.out.println("    add fieldConnection: " + fieldConnection);
                }
                if ((j + 1) < MAX_COLS) {
                    String id = "fieldConnection:" + i + "," + j + "-" + i + "," + (j + 1);
                    DummyFieldConnection fieldConnection = new DummyFieldConnection(id, fieldsArray[i][j], fieldsArray[i][j + 1]);
                    fieldConnections.put(id, fieldConnection);
//                    System.out.println("    add fieldConnection: " + fieldConnection);
                }
            }
        }
        // add FieldConnectionObjects
        fieldConnections.get("fieldConnection:3,0-4,0").addObjectOnConnection(newFieldConnectionObject(new Wall("wall:3,0-4,0")));
        fieldConnections.get("fieldConnection:3,1-4,1").addObjectOnConnection(newFieldConnectionObject(new Door("door:3,1-4,1")));
    }

    private static FieldConnectionObject newFieldConnectionObject(FieldConnectionObject fieldConnectionObject) {
        fieldConnectionObjects.put(fieldConnectionObject.getId(), fieldConnectionObject);
        return fieldConnectionObject;
    }

    private DummyBoard() {
        super("dummyBoard", new HashSet<>(fields.values()), new HashSet<>(fieldConnections.values()));
    }

    public Field getField(int i, int j) {
        return fieldsArray[i][j];
    }

    public Field getField(String id) {
        return fields.get(id);
    }

    public FieldConnection getFieldConnection(String id) {
        return fieldConnections.get(id);
    }

    public <T extends FieldConnectionObject> T getFieldConnectionObject(Class<T> clazz, String id) {
        return (T) fieldConnectionObjects.get(id);
    }

    private static class DummyField extends Field {

        protected DummyField(String id) {
            super(id);
        }
    }

    private static class DummyFieldConnection extends FieldConnection {

        public DummyFieldConnection(String id, Field leftHand, Field rightHand) {
            super(id, leftHand, rightHand);
        }
    }
}
