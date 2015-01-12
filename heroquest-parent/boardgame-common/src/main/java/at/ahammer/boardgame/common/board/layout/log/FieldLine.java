package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.inject.Inject;

/**
 * The representation of a line (as {@link java.lang.String}) of a {@link at.ahammer.boardgame.api.board.field.Field}.
 */
public abstract class FieldLine {

    private final Field field;

    private final int line;

    public FieldLine(Field field, int line) {
        this.field = field;
        this.line = line;
    }

    public String toString(int line, Field field, StringUtil stringUtil) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstChar());
        sb.append(text(line, field, stringUtil));
        sb.append(lastChar());
        return sb.toString();
    }

    protected abstract String text(int line, Field field, StringUtil stringUtil);

    protected String fixedLengthString(String string, StringUtil stringUtil) {
        return stringUtil.padLeftFixSize(string, getMaxInnerWidth());
    }

    protected String fixedLengthString(char c, StringUtil stringUtil) {
        return fixedLengthString(String.valueOf(c), stringUtil);
    }

    public abstract char firstChar();

    public abstract char defaultChar();

    public abstract char lastChar();

    protected int getMaxWidth() {
        return GridLayoutLogger.WIDTH;
    }

    protected int getMaxHeight() {
        return GridLayoutLogger.HEIGHT;
    }

    protected int getMaxInnerWidth() {
        return getMaxWidth() - 2;
    }
}
