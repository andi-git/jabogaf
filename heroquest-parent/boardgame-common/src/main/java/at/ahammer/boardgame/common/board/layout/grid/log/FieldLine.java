package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

/**
 * The representation of a line (as {@link java.lang.String}) of a {@link at.ahammer.boardgame.api.board.field.Field}
 * used in {@link FieldRepresentation}. Every {@link at.ahammer.boardgame.api.board.field.Field} consists of multiple
 * {@link FieldLine}s.
 * <p/>
 * Every concrete {@link FieldLine} has a concrete {@link FieldLineUsage} to specify, when to use the current {@link
 * FieldLine}.
 * <p/>
 * The method {@link #toString()} will be concat the String by:
 * <pre>
 *     <ul>
 *         <li>{@link #firstChar()}</li>
 *         <li>{@link #text()}</li>
 *         <li>{@link #lastChar()}</li>
 *     </ul>
 * </pre>
 */
public abstract class FieldLine {

    public static final int WIDTH = 20;

    public static final int HEIGHT = 10;

    private final StringUtil stringUtil;

    protected FieldLine(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstChar());
        sb.append(fixedLengthString(text()));
        sb.append(lastChar());
        return sb.toString();
    }

    /**
     * The first character of the line.
     *
     * @return the first character of the line
     */
    public abstract char firstChar();

    /**
     * The last character of the line.
     *
     * @return the last character of the line
     */
    public abstract char lastChar();

    /**
     * The text between the first and the last character. The result of this method will be trimmed to a fixed length:
     * {@Link #fixedLengthString}.
     *
     * @return the text between the first and the last character
     */
    protected abstract String text();

    /**
     * If the input-string is longer, it will be trimmed to {@link #getMaxInnerWidth()}. If the input-string is shorter,
     * spaces will be added to fit the length.
     *
     * @param string the input-string
     * @return the input-string trimmed or expanded to the length {@link #getMaxInnerWidth()}
     */
    protected String fixedLengthString(String string) {
        return stringUtil.padLeftFixSize(string, getMaxInnerWidth());
    }

    /**
     * The input-character will be converted to a {@link java.lang.String} with the length of {@link
     * #getMaxInnerWidth()}, filled with spaces.
     *
     * @param c the input-character
     * @return input-character will be converted to a {@link java.lang.String} with the length of {@link
     * #getMaxInnerWidth()}, filled with spaces
     */
    protected String fixedLengthString(char c) {
        return fixedLengthString(String.valueOf(c));
    }

    protected StringUtil getStringUtil() {
        return this.stringUtil;
    }

    /**
     * The maximum inner-width, defined bq {@link FieldLine#WIDTH} reduced by 2 (first and last character).
     *
     * @return maximum inner-width, defined bq {@link FieldLine#WIDTH} reduced by 2 (first and last character)
     */
    protected int getMaxInnerWidth() {
        return WIDTH - 2;
    }

    /**
     * This class knows, when to use a concrete {@link FieldLine} and is a factory for this concrete class.
     *
     * @param <T> the concrete type of {@link FieldLine}
     */
    public static abstract class FieldLineUsage<T extends FieldLine> {

        /**
         * To have the right order of {@link FieldLine}s, this method is used.
         *
         * @return the rank (position) of the current {@link FieldLine}
         */
        public abstract int rank();

        /**
         * Check if it was the last occurrence of {@link FieldLine} in the {@link FieldLineUsage}.
         *
         * @param state the current {@link FactoryForFieldLines.State}
         * @return {@code true} if the last occurrence was reached
         */
        public abstract boolean isLast(FactoryForFieldLines.State state);

        /**
         * Factory-method to create a new concrete {@link FieldLine} of type T.
         *
         * @param state the current {@link FactoryForFieldLines.State}
         * @return a new concrete {@link FieldLine} of type T
         */
        public abstract T create(FactoryForFieldLines.State state);

        /**
         * Check if at least one occurrence of the concrete {@link FieldLine} should be used.
         *
         * @return {@code true}, if at least one occurrence of the concrete {@link FieldLine} should be used
         */
        public abstract boolean atLeastOnce();

        protected int getMaxHeight() {
            return FieldLine.HEIGHT;
        }

        /**
         * Check if the last element was already reached, i. e. if there is a line to add the element to.
         *
         * @param state - the current {@link FactoryForFieldLines.State}
         * @return {@code true} if the last element is not reached, i. e. a new element can be added
         */
        protected boolean isPreLastElement(FactoryForFieldLines.State state) {
            return state.getFieldLines().size() == getMaxHeight() - 1;
        }

        protected <T extends FieldLine> long countAlreadyExistingFieldLineElements(FactoryForFieldLines.State state, Class<T> type) {
            return state.getFieldLines().stream().filter(fl -> type.isAssignableFrom(fl.getClass())).count();
        }
    }
}