package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.behavior.look.LookNotPossibleException;
import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.board.layout.log.LayoutLoggerManager;
import org.jabogaf.core.behavior.look.LookPathBasic;
import org.jabogaf.core.behavior.look.LookPathNull;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.util.stream.StreamUtil;

import javax.inject.Inject;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.stream.Stream;

/**
 * A {@link Layout} based on a grid. To create this layout, a concrete instance of {@link GridLayoutCreationStrategy} is
 * needed.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class GridLayout extends LayoutBasic {

    @Inject
    private StreamUtil streamUtil;

    @Inject
    private LayoutLoggerManager layoutLoggerManager;

    private final Field[][] fields;

    private Map<String, LookPath> lookPaths = new HashMap<>();

    public GridLayout(String id, GridLayoutCreationStrategy gridLayoutCreationStrategy) {
        super(id, gridLayoutCreationStrategy.getFields(), gridLayoutCreationStrategy.getFieldConnections(), gridLayoutCreationStrategy.getFieldGroups());
        fields = gridLayoutCreationStrategy.getFieldsArray();
    }

    @Override
    public void initAfterBoard() {
        calculateAllFieldLooks();
    }

    private void calculateAllFieldLooks() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                Field fieldFrom = fields[i][j];
                for (int n = 0; n < fields.length; n++) {
                    for (int m = 0; m < fields[0].length; m++) {
                        Field fieldTarget = fields[n][m];
                        if (!fieldFrom.equals(fieldTarget)) {
                            List<Field> allFieldsInLook = getAllFieldsInLook(fieldFrom, fieldTarget);
                            lookPaths.put(createKeyFor2Fields(fieldFrom, fieldTarget), new LookPathBasic(allFieldsInLook));
                        }
                    }
                }
            }
        }
    }

    private String createKeyFor2Fields(Field fieldFrom, Field fieldTo) {
        return fieldFrom.getId() + fieldTo.getId();
    }

    @Override
    public LookPath getLookPath(Field fieldFrom, Field fieldTo) {
        String key = createKeyFor2Fields(fieldFrom, fieldTo);
        if (!lookPaths.containsKey(key)) {
            return new LookPathNull();
        } else {
            return lookPaths.get(key);
        }
    }

    @Override
    public List<LayoutActionImpact<?, ?>> getAllLayoutActionImpacts(Field fieldFrom, Field fieldTo) {
        List<LayoutActionImpact<?, ?>> layoutActionImpacts = new ArrayList<>();
        String key = createKeyFor2Fields(fieldFrom, fieldTo);
        if (lookPaths.containsKey(key)) {
            layoutActionImpacts.addAll(lookPaths.get(key).getLayoutActionImpacts());
        }
        return Collections.unmodifiableList(layoutActionImpacts);
    }

    private List<Field> getAllFieldsInLook(Field fieldFrom, Field fieldTo) {
        Coordinate coordinateFrom = getCoordinate(fieldFrom);
        Coordinate coordinateTo = getCoordinate(fieldTo);
        Line2D.Double line = new Line2D.Double(getCoordinateForLine(coordinateFrom.getX()), getCoordinateForLine(coordinateFrom.getY()), getCoordinateForLine(coordinateTo.getX()), getCoordinateForLine(coordinateTo.getY()));
        // get all fields in look
        List<Field> fieldsInLook = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (line.intersects(i, j, 1.0, 1.0)) {
                    fieldsInLook.add(fields[i][j]);
                }
            }
        }
        return Collections.unmodifiableList(fieldsInLook);
    }

    /**
     * Get the {@link Field} on position x / y.
     *
     * @param x the position of x-coordinate
     * @param y the position of y-coordinate
     * @return the {@link Field} on position x / y
     */
    public Field getField(int x, int y) {
        return fields[y][x];
    }

    /**
     * Get the max count of the x-coordinate.
     *
     * @return the max count of the x-coordinate
     */
    public int getMaxX() {
        return fields[0].length;
    }

    /**
     * Get the max count of the y-coordinate.
     *
     * @return the max count of the y-coordinate
     */
    public int getMaxY() {
        return fields.length;
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

    @Override
    public Stream<Field> getFieldsAsStream() {
        return streamUtil.getTwoDimensionalArrayAsStream(fields);
    }

    public Field[][] getFieldsAsArray() {
        return fields;
    }

    @Override
    public String toString() {
        return layoutLoggerManager.toString(this, null);
    }
}
