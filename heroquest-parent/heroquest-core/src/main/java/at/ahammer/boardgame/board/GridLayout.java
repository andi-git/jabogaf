package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldConnection;
import at.ahammer.boardgame.object.field.FieldConnectionObject;

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
}
