package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The representation of a complete {@link at.ahammer.boardgame.api.board.field.Field} to log.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldRepresentation {

    @Inject
    private FieldLineFactory fieldLineFactory;

    @Inject
    private PlayerController playerController;

    @Inject
    private StringUtil stringUtil;

    private final Field field;

    private FieldLine[] fieldLines;

    public FieldRepresentation(Field field) {
        this.field = field;
    }

    public String toString(int line) {
        return getFieldLines()[line].toString(line, field, stringUtil);
    }

    private FieldLine[] getFieldLines() {
        if (fieldLines == null) {
            fieldLines = new FieldLine[GridLayoutLogger.HEIGHT];
            for (int line = 0; line < GridLayoutLogger.HEIGHT; line++) {
                fieldLines[line] = fieldLineFactory.get(field, line);
            }
        }
        return fieldLines;
    }
}
