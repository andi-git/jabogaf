package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.util.string.StringUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a complete {@link org.jabogaf.api.board.field.Field} to log.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldRepresentation implements GridLayoutLoggerElement {

    private final Field field;

    @Inject
    private FactoryForFieldLines factoryForFieldLines;

    @Inject
    private PlayerController playerController;

    @Inject
    private StringUtil stringUtil;

    private List<FieldLine.Representation> fieldLineRepresentations = new ArrayList<>();

    public FieldRepresentation(Field field) {
        this.field = field;
    }

    @Override
    public String toString(int line, GridLayoutLoggerParameter parameter) {
        return getFieldLines(parameter).get(line).toString();
    }

    private List<FieldLine.Representation> getFieldLines(GridLayoutLoggerParameter parameter) {
        if (fieldLineRepresentations.isEmpty()) {
            fieldLineRepresentations.addAll(factoryForFieldLines.create(field, collectGameContextBeansOnField(), parameter));
        }
        return fieldLineRepresentations;
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
