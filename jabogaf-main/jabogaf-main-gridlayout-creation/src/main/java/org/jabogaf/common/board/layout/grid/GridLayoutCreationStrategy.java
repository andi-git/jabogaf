package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.board.layout.KeyTwoFields;
import org.jabogaf.api.board.layout.LayoutCreation;
import org.jabogaf.common.board.layout.KeyTwoFieldsBasic;

import java.awt.geom.Line2D;
import java.util.*;

/**
 * The strategy to create a {@code GridLayout}.
 */
public class GridLayoutCreationStrategy implements LayoutCreation {

    protected final Field[][] fieldsArray;

    protected final Map<String, Field> fields;

    protected final Map<String, FieldConnection> fieldConnections;

    protected final Map<String, FieldGroup> fieldGroups;

    protected final Map<KeyTwoFields, LookPath> lookPaths = new HashMap<>();

    public GridLayoutCreationStrategy(
            int maxX,
            int maxY,
            FieldCreation fieldCreation,
            FieldConnectionCreation fieldConnectionCreation,
            LookPathCreation lookPathCreation) {
        fieldsArray = new Field[maxY][maxX];
        fields = new HashMap<>();
        fieldConnections = new HashMap<>();
        fieldGroups = new HashMap<>();

        createFields(maxX, maxY, fieldCreation);
        createFieldConnections(fieldConnectionCreation);
        createFieldConnectionObjects();
        createFieldGroups();
        createLookPaths(lookPathCreation);
    }

    protected final void createFields(int maxX, int maxY, FieldCreation fieldCreation) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                fieldsArray[y][x] = fieldCreation.create(x, y);
                fields.put(fieldsArray[y][x].getId(), fieldsArray[y][x]);
            }
        }
    }

    protected final void createFieldConnections(FieldConnectionCreation fieldConnectionCreation) {
        for (int y = 0; y < fieldsArray.length; y++) {
            for (int x = 0; x < fieldsArray[y].length; x++) {
                if (y != fieldsArray.length - 1) {
                    FieldConnection fieldConnection = fieldConnectionCreation.create(fieldsArray[y][x], fieldsArray[y + 1][x]);
                    fieldConnections.put(fieldConnection.getId(), fieldConnection);
                }
                if (x != fieldsArray[y].length - 1) {
                    FieldConnection fieldConnection = fieldConnectionCreation.create(fieldsArray[y][x], fieldsArray[y][x + 1]);
                    fieldConnections.put(fieldConnection.getId(), fieldConnection);
                }
            }
        }
    }

    protected void createFieldConnectionObjects() {
    }

    protected void createFieldGroups() {
    }

    public final Field[][] getFieldsArray() {
        Field[][] result = new Field[fieldsArray.length][fieldsArray[0].length];
        for (int y = 0; y < fieldsArray.length; y++) {
            System.arraycopy(fieldsArray[y], 0, result[y], 0, fieldsArray[0].length);
        }
        return result;
    }

    @Override
    public final Set<Field> getFields() {
        return new HashSet<>(fields.values());
    }

    @Override
    public final Set<FieldConnection> getFieldConnections() {
        return new HashSet<>(fieldConnections.values());
    }

    @Override
    public final Set<FieldGroup> getFieldGroups() {
        return new HashSet<>(fieldGroups.values());
    }

    @Override
    public Map<KeyTwoFields, LookPath> getLookPaths() {
        return Collections.unmodifiableMap(lookPaths);
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

    protected void createLookPaths(LookPathCreation lookPathCreation) {
        for (int i = 0; i < fieldsArray.length; i++) {
            for (int j = 0; j < fieldsArray[0].length; j++) {
                Field fieldFrom = fieldsArray[i][j];
                for (int n = 0; n < fieldsArray.length; n++) {
                    for (int m = 0; m < fieldsArray[0].length; m++) {
                        Field fieldTarget = fieldsArray[n][m];
                        if (!fieldFrom.equals(fieldTarget)) {
                            List<Field> allFieldsInLook = getAllFieldsInLook(fieldFrom, fieldTarget);
                            lookPaths.put(new KeyTwoFieldsBasic(fieldFrom, fieldTarget), lookPathCreation.create(allFieldsInLook));
                        }
                    }
                }
            }
        }
    }

    protected List<Field> getAllFieldsInLook(Field fieldFrom, Field fieldTo) {
        Coordinate coordinateFrom = getCoordinate(fieldFrom);
        Coordinate coordinateTo = getCoordinate(fieldTo);
        Line2D.Double line = new Line2D.Double(getCoordinateForLine(coordinateFrom.getX()), getCoordinateForLine(coordinateFrom.getY()), getCoordinateForLine(coordinateTo.getX()), getCoordinateForLine(coordinateTo.getY()));
        // get all fields in look
        List<Field> fieldsInLook = new ArrayList<>();
        for (int i = 0; i < fieldsArray.length; i++) {
            for (int j = 0; j < fieldsArray[i].length; j++) {
                if (line.intersects(i, j, 1.0, 1.0)) {
                    fieldsInLook.add(fieldsArray[i][j]);
                }
            }
        }
        return Collections.unmodifiableList(fieldsInLook);
    }

    private Coordinate getCoordinate(Field field) {
        Coordinate result = null;
        for (int i = 0; i < fieldsArray.length; i++) {
            for (int j = 0; j < fieldsArray[i].length; j++) {
                if (fieldsArray[i][j].equals(field)) {
                    result = new Coordinate(i, j);
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

    private double getCoordinateForLine(int i) {
        // the coordinate for the line starts in the middle of the field
        return ((double) i) + 0.5;
    }

    private static class Coordinate {

        private final int x;
        private final int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
