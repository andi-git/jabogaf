package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.layout.log.LayoutLogger;
import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.common.board.layout.grid.GridLayout;
import at.ahammer.boardgame.util.array.ArrayUtil;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GridLayoutLogger implements LayoutLogger<GridLayout, GridLayoutLoggerParameter> {

    @Inject
    private ArrayUtil arrayUtil;

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private StringUtil stringUtil;

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean canHandle(Layout layout) {
        return layout != null && layout.getClass() == genericType();
    }

    @Override
    public Class<? extends Layout> genericType() {
        return GridLayout.class;
    }

    @Override
    public String toString(GridLayout layout, GridLayoutLoggerParameter parameter) {
        Field[][] fieldsArray = layout.getFieldsAsArray();
        if (fieldsArray.length == 0 || fieldsArray[0].length == 0) {
            return GridLayout.class.getSimpleName() + " is empty!";
        }

        // create an array with all GridLayoutLoggerElements
        int logArrayMaxY = layout.getMaxY() * 2 - 1;
        int logArrayMaxX = layout.getMaxX() * 2 - 1;
        GridLayoutLoggerElement[][] gridLayoutLoggerElements = new GridLayoutLoggerElement[logArrayMaxY][logArrayMaxX];
        for (int y = 0; y < logArrayMaxY; y++) {
            for (int x = 0; x < logArrayMaxX; x++) {
                gridLayoutLoggerElements[y][x] = ElementDetector.get(x, y).create(layout, x, y, gameContextManager);
            }
        }

        // create a StringBuilder with the GridLayout as String
        StringBuilder sb = new StringBuilder();
        sb.append("id:");
        sb.append(layout.getId());
        sb.append("\n");
        for (int i = 0; i < gridLayoutLoggerElements.length; i++) {
            int height = FieldLine.HEIGHT;
            if (i % 2 == 1) {
                height = 1;
            }
            for (int h = 0; h < height; h++) {
                for (int j = 0; j < gridLayoutLoggerElements[i].length; j++) {
                    sb.append(gridLayoutLoggerElements[i][j].toString(h, parameter));
                    if (j == gridLayoutLoggerElements[i].length - 1) {
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    private FieldRepresentation[][] convert(Field[][] fields) {
        return arrayUtil.convertTwoDimensionalArray(fields, FieldRepresentation.class, (field) -> gameContextManager.resolve(new FieldRepresentation(field)));
    }

    private enum ElementDetector {
        Field {
            @Override
            protected boolean canBeUsed(int x, int y) {
                return x % 2 == 0 && y % 2 == 0;
            }

            @Override
            public GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager) {
                Field field = layout.getField((x / 2), (y / 2));
                return resolve(new FieldRepresentation(field), gameContextManager);
            }
        },
        FieldConnectorVertical {
            @Override
            protected boolean canBeUsed(int x, int y) {
                return x % 2 == 1 && y % 2 == 0;
            }

            @Override
            public GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager) {
                Field fieldLeftHand = layout.getField(((x - 1) / 2), (y / 2));
                Field fieldRightHand = layout.getField(((x + 1) / 2), (y / 2));
                return resolve(new FieldConnectionVerticalRepresentation(layout.getConnection(fieldLeftHand, fieldRightHand)), gameContextManager);
            }
        },
        FieldConnectorHorizontal {
            @Override
            protected boolean canBeUsed(int x, int y) {
                return x % 2 == 0 && y % 2 == 1;
            }

            @Override
            public GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager) {
                Field fieldLeftHand = layout.getField((x / 2), ((y - 1) / 2));
                Field fieldRightHand = layout.getField((x / 2), ((y + 1) / 2));
                return resolve(new FieldConnectionHorizontalRepresentation(layout.getConnection(fieldLeftHand, fieldRightHand)), gameContextManager);
            }
        },
        FieldConnectorSpace {
            @Override
            protected boolean canBeUsed(int x, int y) {
                return x % 2 == 1 && y % 2 == 1;
            }

            @Override
            public GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager) {
                return new FieldConnectionSpaceRepresentation();
            }
        },
        Null {
            @Override
            protected boolean canBeUsed(int x, int y) {
                return false;
            }

            @Override
            public GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager) {
                throw new RuntimeException("Should never end up here!");
            }
        };

        public static ElementDetector get(int x, int y) {
            ElementDetector result = ElementDetector.Null;
            for (ElementDetector elementDetector : values()) {
                if (elementDetector.canBeUsed(x, y)) {
                    result = elementDetector;
                    break;
                }
            }
            return result;
        }

        protected abstract boolean canBeUsed(int x, int y);

        public abstract GridLayoutLoggerElement create(GridLayout layout, int x, int y, GameContextManager gameContextManager);

        protected <T> T resolve(T object, GameContextManager gameContextManager) {
            return gameContextManager.resolve(object);
        }
    }
}