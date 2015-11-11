package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.log.LayoutLoggerManager;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.util.stream.StreamUtil;

import javax.inject.Inject;
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

    public GridLayout(String id, GridLayoutCreationStrategy gridLayoutCreationStrategy) {
        super(id, gridLayoutCreationStrategy);
        fields = gridLayoutCreationStrategy.getFieldsArray();
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
