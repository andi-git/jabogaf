package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.util.log.SLF4J;
import org.jabogaf.util.string.StringUtil;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Factory class to create all {@link FieldLine}s for a {@link Field}.
 * <p/>
 * The creation ({@link FieldLine#create(FactoryForFieldLines.State)}) and order ({@link FieldLine#rank()}) of a
 * concrete {@link FieldLine} is handled by it's {@link FieldLine}.
 * <p/>
 * The maximum width of the {@link FieldLine} is defined by {@link FieldLine.Representation#WIDTH}.
 * <p/>
 * The maximum number of {@link FieldLine}s is defined by {@link FieldLine.Representation#HEIGHT}.
 * <p/>
 * At the moment, the order of a field is:
 * <pre>
 *      <ul>
 *          <li>{@link FieldLineFirst} (1)</li>
 *          <li>{@link FieldLineFieldName} (1)</li>
 *          <li>{@link FieldLineFieldGroup} (number of FieldGroups)</li>
 *          <li>{@link FieldLineGameContextBean} (number of GameContextBeans on Field)</li>
 *          <li>{@link FieldLineMoveableFields}</li>
 *          <li>{@link FieldLineEmpty} (filler)</li>
 *          <li>{@link FieldLineLast} (1)</li>
 *      </ul>
 * </pre>
 */
@ApplicationScoped
public class FactoryForFieldLines {

    @Inject
    @Any
    private Instance<FieldLine<? extends FieldLine.Representation>> fieldLines;

    @Inject
    private StringUtil stringUtil;

    @Inject
    @SLF4J
    private Logger log;

    /**
     * Create all {@link FieldLine}s for the {@link Field}.
     *
     * @param field            the {@link Field} to create
     * @param gameContextBeans a list off all {@link GameContextBean}s
     * @param parameter        the current {@link GridLayoutLoggerParameter}
     * @return a list of {@link FieldLine}s representing the {@link Field}
     */
    public List<FieldLine.Representation> create(Field field, List<GameContextBean> gameContextBeans, GridLayoutLoggerParameter parameter) {
        State state = new State(field, gameContextBeans, getOrderedFieldLines(), stringUtil, parameter);
        for (FieldLine fieldLine : state.getFieldLines()) {
            // if there should be at least one, add it
            if (fieldLine.atLeastOnce()) {
                state.addToFieldLineRepresentations(fieldLine.create(state));
            }
            // add all after the last element is reached
            while (!fieldLine.isLast(state)) {
                state.addToFieldLineRepresentations(fieldLine.create(state));
            }
        }
        return state.getFieldLineRepresentations();
    }

    /**
     * Get all {@link FieldLine} sorted by {@link FieldLine#rank()}.
     *
     * @return a sorted {@link List} of {@link FieldLine}
     */
    private List<FieldLine<? extends FieldLine.Representation>> getOrderedFieldLines() {
        List<FieldLine<? extends FieldLine.Representation>> ordered = new ArrayList<>();
        fieldLines.forEach(ordered::add);
        ordered.sort(Comparator.comparingInt(FieldLine::rank));
        return ordered;
    }

    /**
     * This class holds the state for the creation of all {@link FieldLine}s of a {@link FieldRepresentation}.
     */
    public static class State {

        private final Field field;

        private final List<GameContextBean> gameContextBeans;

        private final List<FieldLine<? extends FieldLine.Representation>> fieldLines;

        private final List<FieldLine.Representation> fieldLineRepresentations = new ArrayList<>();

        private final StringUtil stringUtil;

        private final GridLayoutLoggerParameter parameter;

        public State(Field field, List<GameContextBean> gameContextBeans, List<FieldLine<? extends FieldLine.Representation>> fieldLines, StringUtil stringUtil, GridLayoutLoggerParameter parameter) {
            this.field = field;
            this.gameContextBeans = gameContextBeans;
            this.fieldLines = fieldLines;
            this.stringUtil = stringUtil;
            this.parameter = parameter != null ? parameter : new GridLayoutLoggerParameter();
        }

        /**
         * Get the underlying {@link Field}.
         *
         * @return the underlying {@link Field}
         */
        public Field getField() {
            return field;
        }

        /**
         * Get a sorted {@link List} of all {@link GameContextBean}s available on
         * the underlying {@link Field}.
         *
         * @return a sorted {@link java.util.List} of all {@link GameContextBean}s
         * available on the underlying {@link Field}
         */
        public List<GameContextBean> getGameContextBeans() {
            return gameContextBeans;
        }

        /**
         * Get a sorted {@link List} of all available {@link FieldLine}s.
         *
         * @return a sorted {@link List} of all available {@link FieldLine}s
         */
        public List<FieldLine<? extends FieldLine.Representation>> getFieldLines() {
            return Collections.unmodifiableList(fieldLines);
        }

        public List<FieldLine.Representation> getFieldLineRepresentations() {
            return Collections.unmodifiableList(fieldLineRepresentations);
        }

        public void addToFieldLineRepresentations(FieldLine.Representation fieldLineRepresentation) {
            fieldLineRepresentations.add(fieldLineRepresentation);
        }

        public void clearFieldLineRepresentations() {
            fieldLineRepresentations.clear();
        }

        public StringUtil getStringUtil() {
            return stringUtil;
        }

        public GridLayoutLoggerParameter getParameter() {
            return parameter;
        }
    }
}
