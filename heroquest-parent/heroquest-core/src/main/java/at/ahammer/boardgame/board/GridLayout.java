package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldConnectionObject;

import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 8/24/14.
 */
public class GridLayout extends Layout {

    private final Field[][] fields;

    public GridLayout(String id, GridLayoutCreation gridLayoutCreation) {
        super(id, gridLayoutCreation.getFields(), gridLayoutCreation.getFieldConnections(), gridLayoutCreation.getFieldGroups());
        fields = gridLayoutCreation.getFieldsArray();
    }

    public Field getField(int i, int j) {
        return fields[i][j];
    }

    private Coordinate getCoordinate(Field field) {
        Coordinate result = null;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j].equals(field)) {
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

    @Override
    public Set<FieldConnection> getLookConnections(Field fieldFrom, Field fieldTo) {
        Set<FieldConnection> result = new HashSet<>();
        Coordinate coordinateFrom = getCoordinate(fieldFrom);
        Coordinate coordinateTo= getCoordinate(fieldTo);
        Line2D.Double line = new Line2D.Double(getCoordinateForLine(coordinateFrom.getX()), getCoordinateForLine(coordinateFrom.getY()), getCoordinateForLine(coordinateTo.getX()), getCoordinateForLine(coordinateTo.getY()));
        // get all fields in look
        Set<Field> fieldsInLook = new HashSet<>();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length ; j++) {
                if (line.intersects(i, j, 1.0, 1.0)) {
                    fieldsInLook.add(fields[i][j]);
                }
            }
        }
        // get all connections of this fields
        for (Field field : fieldsInLook) {
            for (Field fieldConnectedTo : fieldsInLook) {
                if (field.isConnected(fieldConnectedTo)) {
                    result.add(field.getConnectionTo(fieldConnectedTo));
                }
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
