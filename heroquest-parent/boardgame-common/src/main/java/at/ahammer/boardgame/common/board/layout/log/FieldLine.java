package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.util.string.StringUtil;

/**
 * The representation of a line (as {@link java.lang.String}) of a {@link at.ahammer.boardgame.api.board.field.Field}
 * used in {@link at.ahammer.boardgame.common.board.layout.log.FieldRepresentation}. Every {@link
 * at.ahammer.boardgame.api.board.field.Field} consists of multiple {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}s.
 * <p/>
 * Every concrete {@link at.ahammer.boardgame.common.board.layout.log.FieldLine} has a concrete {@link
 * at.ahammer.boardgame.common.board.layout.log.FieldLineUsage} to specify, when to use the current {@link
 * at.ahammer.boardgame.common.board.layout.log.FieldLine}.
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
     * The maximum inner-width, defined bq {@link at.ahammer.boardgame.common.board.layout.log.GridLayoutLogger#WIDTH}
     * reduced by 2 (first and last character).
     *
     * @return maximum inner-width, defined bq {@link at.ahammer.boardgame.common.board.layout.log.GridLayoutLogger#WIDTH}
     * reduced by 2 (first and last character)
     */
    protected int getMaxInnerWidth() {
        return GridLayoutLogger.WIDTH - 2;
    }

}
