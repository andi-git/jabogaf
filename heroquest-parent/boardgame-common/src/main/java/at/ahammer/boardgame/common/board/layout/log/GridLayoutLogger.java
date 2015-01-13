package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.layout.log.AbstractLayoutLogger;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.common.board.layout.GridLayout;
import at.ahammer.boardgame.util.array.ArrayUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GridLayoutLogger extends AbstractLayoutLogger<GridLayout> {

    public static final int WIDTH = 20;

    public static final int HEIGHT = 10;

    @Inject
    private ArrayUtil arrayUtil;

    @Inject
    private GameContextManager gameContextManager;

    @Override
    public Class genericType() {
        return GridLayout.class;
    }

    @Override
    public String toString(GridLayout layout) {
        FieldRepresentation[][] fieldRepresentations = convert(layout.getFieldsAsArray());
        StringBuilder sb = new StringBuilder();
        sb.append("id:");
        sb.append(layout.getId());
        sb.append("\n");
        for (int i = 0; i < fieldRepresentations.length; i++) {
            for (int h = 0; h < HEIGHT; h++) {
                for (int j = 0; j < fieldRepresentations[i].length; j++) {
                    sb.append(fieldRepresentations[i][j].toString(h));
                    if (j == fieldRepresentations[i].length - 1) {
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    private FieldRepresentation[][] convert(Field[][] fields) {
        return arrayUtil.convertTwoDimensionalArray(fields, FieldRepresentation.class, (field) -> {
                return gameContextManager.resolve(new FieldRepresentation(field));
            });
    }

}