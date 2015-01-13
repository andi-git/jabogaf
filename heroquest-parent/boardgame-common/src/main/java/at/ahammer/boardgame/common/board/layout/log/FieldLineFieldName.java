package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.util.string.StringUtil;

/**
 * The line to add the name of a {@link at.ahammer.boardgame.api.board.field.Field}.
 */
public class FieldLineFieldName extends FieldLine {

    private final Field field;

    public FieldLineFieldName(Field field, StringUtil stringUtil) {
        super(stringUtil);
        this.field = field;
    }

    @Override
    public String text() {
        return field.getId();
    }

    @Override
    public char firstChar() {
        return '|';
    }

    @Override
    public char lastChar() {
        return '|';
    }
}
