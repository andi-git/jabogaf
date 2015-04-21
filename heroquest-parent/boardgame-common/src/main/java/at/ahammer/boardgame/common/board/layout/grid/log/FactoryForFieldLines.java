package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.util.string.StringUtil;
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
 * Factory class to create all {@link FieldLine}s for a {@link at.ahammer.boardgame.api.board.field.Field}.
 * <p/>
 * The creation ({@link FieldLine.FieldLineUsage#create(FactoryForFieldLines.State)}) and order ({@link
 * FieldLine.FieldLineUsage#rank()}) of a concrete {@link FieldLine} is handled by it's {@link
 * FieldLine.FieldLineUsage}.
 * <p/>
 * The maximum width of the {@link FieldLine} is defined by {@link FieldLine#WIDTH}.
 * <p/>
 * The maximum number of {@link FieldLine}s is defined by {@link FieldLine#HEIGHT}.
 * <p/>
 * At the moment, the order of a field is:
 * <pre>
 *      <ul>
 *          <li>{@link FieldLineFirst} (1)</li>
 *          <li>{@link FieldLineFieldName} (1)</li>
 *          <li>{@link FieldLineFieldGroup} (number of FieldGroups)</li>
 *          <li>{@link FieldLineGameContextBean} (number of
 * GameContextBeans on Field)</li>
 *          <li>{@link FieldLineEmpty} (filler)</li>
 *          <li>{@link FieldLineLast} (1)</li>
 *      </ul>
 * </pre>
 */
@ApplicationScoped
public class FactoryForFieldLines {

    @Inject
    @Any
    private Instance<FieldLine.FieldLineUsage<? extends FieldLine>> fieldLineUsages;

    @Inject
    private StringUtil stringUtil;

    @Inject
    private Logger log;

    /**
     * Create all {@link FieldLine}s for the {@link at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param field            the {@link Field} to create
     * @param gameContextBeans a list off all {@link GameContextBean}s
     * @param parameter
     * @return a list of {@link FieldLine}s representing the {@link Field}
     */
    public List<FieldLine> create(Field field, List<GameContextBean> gameContextBeans, GridLayoutLoggerParameter parameter) {
        State state = new State(field, gameContextBeans, getOrderedFieldLineUsages(), stringUtil, parameter);
        for (FieldLine.FieldLineUsage fieldLineUsage : state.getFieldLineUsages()) {
            // if there should be at least one, add it
            if (fieldLineUsage.atLeastOnce()) {
                state.addToFieldLines(fieldLineUsage.create(state));
            }
            // add all after the last element is reached
            while (!fieldLineUsage.isLast(state)) {
                state.addToFieldLines(fieldLineUsage.create(state));
            }
        }
        return state.getFieldLines();
    }

    /**
     * Get all {@link FieldLine.FieldLineUsage} sorted by {@link FieldLine.FieldLineUsage#rank()}.
     *
     * @return a sorted {@link java.util.List} of {@link FieldLine.FieldLineUsage}
     */
    private List<FieldLine.FieldLineUsage<? extends FieldLine>> getOrderedFieldLineUsages() {
        List<FieldLine.FieldLineUsage<? extends FieldLine>> ordered = new ArrayList<>();
        fieldLineUsages.forEach(ordered::add);
        ordered.sort(Comparator.comparingInt(FieldLine.FieldLineUsage::rank));
        return ordered;
    }

    /**
     * This class holds the state for the creation of all {@link FieldLine}s of a {@link FieldRepresentation}.
     */
    public static class State {

        private final Field field;
        private final List<GameContextBean> gameContextBeans;
        private final List<FieldLine.FieldLineUsage<? extends FieldLine>> fieldLineUsages;
        private final List<FieldLine> fieldLines = new ArrayList<>();
        private final StringUtil stringUtil;
        private final GridLayoutLoggerParameter parameter;

        public State(Field field, List<GameContextBean> gameContextBeans, List<FieldLine.FieldLineUsage<? extends FieldLine>> fieldLineUsages, StringUtil stringUtil, GridLayoutLoggerParameter parameter) {
            this.field = field;
            this.gameContextBeans = gameContextBeans;
            this.fieldLineUsages = fieldLineUsages;
            this.stringUtil = stringUtil;
            this.parameter = parameter != null ? parameter : new GridLayoutLoggerParameter();
        }

        /**
         * Get the underlying {@link at.ahammer.boardgame.api.board.field.Field}.
         *
         * @return the underlying {@link at.ahammer.boardgame.api.board.field.Field}
         */
        public Field getField() {
            return field;
        }

        /**
         * Get a sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.cdi.GameContextBean}s available on
         * the underlying {@link at.ahammer.boardgame.api.board.field.Field}.
         *
         * @return a sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.cdi.GameContextBean}s
         * available on the underlying {@link at.ahammer.boardgame.api.board.field.Field}
         */
        public List<GameContextBean> getGameContextBeans() {
            return gameContextBeans;
        }

        /**
         * Get a sorted {@link java.util.List} of all available {@link FieldLine.FieldLineUsage}s.
         *
         * @return a sorted {@link java.util.List} of all available {@link FieldLine.FieldLineUsage}s
         */
        public List<FieldLine.FieldLineUsage<? extends FieldLine>> getFieldLineUsages() {
            return Collections.unmodifiableList(fieldLineUsages);
        }

        public List<FieldLine> getFieldLines() {
            return Collections.unmodifiableList(fieldLines);
        }

        public void addToFieldLines(FieldLine fieldLine) {
            fieldLines.add(fieldLine);
        }

        public void clearFieldLines() {
            fieldLines.clear();
        }

        public StringUtil getStringUtil() {
            return stringUtil;
        }

        public GridLayoutLoggerParameter getParameter() {
            return parameter;
        }
    }
}
