package at.ahammer.boardgame.common.board.layout.log;

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
 * Factory class to create all {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s for a {@link
 * at.ahammer.boardgame.api.board.field.Field}.
 * <p/>
 * The creation ({@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage#create(at.ahammer.boardgame.common.board.layout.log.FieldLineFactory.State)})
 * and order ({@link FieldLineUsage#rank()}) of a concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}
 * is handled by it's {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage}.
 * <p/>
 * The maximum width of the {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} is defined by {@link
 * at.ahammer.boardgame.common.board.layout.log.FieldLine#WIDTH}.
 * <p/>
 * The maximum number of {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s is defined by {@link
 * at.ahammer.boardgame.common.board.layout.log.FieldLine#HEIGHT}.
 * <p/>
 * At the moment, the order of a field is:
 * <pre>
 *      <ul>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineFirst} (1)</li>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineFieldName} (1)</li>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineFieldGroup} (number of FieldGroups)</li>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineGameContextBean} (number of
 * GameContextBeans on Field)</li>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineEmpty} (filler)</li>
 *          <li>{@link at.ahammer.boardgame.common.board.layout.log.FieldLineLast} (1)</li>
 *      </ul>
 * </pre>
 */
@ApplicationScoped
public class FieldLineFactory {

    @Inject
    @Any
    private Instance<FieldLineUsage<? extends FieldLine>> fieldLineUsages;

    @Inject
    private StringUtil stringUtil;

    @Inject
    private Logger log;

    /**
     * Create all {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s for the {@link
     * at.ahammer.boardgame.api.board.field.Field}.
     *
     * @param field
     * @param gameContextBeans
     * @return
     */
    public List<FieldLine> create(Field field, List<GameContextBean> gameContextBeans) {
        State state = new State(field, gameContextBeans, getOrderedFieldLineUsages(), stringUtil);
        for (FieldLineUsage fieldLineUsage : state.getFieldLineUsages()) {
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
     * Get all {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage} sorted by {@link
     * FieldLineUsage#rank()}.
     *
     * @return a sorted {@link java.util.List} of {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage}
     */
    private List<FieldLineUsage<? extends FieldLine>> getOrderedFieldLineUsages() {
        List<FieldLineUsage<? extends FieldLine>> ordered = new ArrayList<>();
        fieldLineUsages.forEach(flu -> ordered.add(flu));
        ordered.sort(Comparator.comparingInt(FieldLineUsage::rank));
        return ordered;
    }

    /**
     * This class holds the state for the creation of all {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s
     * of a {@link at.ahammer.boardgame.common.board.layout.log.FieldRepresentation}.
     */
    public static class State {

        private final Field field;
        private final List<GameContextBean> gameContextBeans;
        private final List<FieldLineUsage<? extends FieldLine>> fieldLineUsages;
        private final List<FieldLine> fieldLines = new ArrayList<>();
        private final StringUtil stringUtil;

        public State(Field field, List<GameContextBean> gameContextBeans, List<FieldLineUsage<? extends FieldLine>> fieldLineUsages, StringUtil stringUtil) {
            this.field = field;
            this.gameContextBeans = gameContextBeans;
            this.fieldLineUsages = fieldLineUsages;
            this.stringUtil = stringUtil;
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
         * Get a sorted {@link java.util.List} of all available {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage}s.
         *
         * @return a sorted {@link java.util.List} of all available {@link at.ahammer.boardgame.common.board.layout.log.FieldLineUsage}s
         */
        public List<FieldLineUsage<? extends FieldLine>> getFieldLineUsages() {
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
    }
}
