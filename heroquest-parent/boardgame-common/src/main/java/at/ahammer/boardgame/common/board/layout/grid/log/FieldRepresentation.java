package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a complete {@link at.ahammer.boardgame.api.board.field.Field} to log.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldRepresentation implements GridLayoutLoggerElement {

    private final Field field;
    @Inject
    private FieldLineFactory fieldLineFactory;
    @Inject
    private PlayerController playerController;
    @Inject
    private StringUtil stringUtil;
    private List<FieldLine> fieldLines = new ArrayList<>();

    public FieldRepresentation(Field field) {
        this.field = field;
    }

    @Override
    public String toString(int line) {
        return getFieldLines().get(line).toString();
    }

    private List<FieldLine> getFieldLines() {
        if (fieldLines.isEmpty()) {
            fieldLines.addAll(fieldLineFactory.create(field, collectGameContextBeansOnField()));
        }
        return fieldLines;
    }

    private List<GameContextBean> collectGameContextBeansOnField() {
        List<GameContextBean> gameContextBeans = new ArrayList<>();
        // add all GameSubjects
        gameContextBeans.addAll(field.getGameSubjects());
        // add other things...
        // ... in future, e.g. GameObjects ;)
        return gameContextBeans;
    }

}
