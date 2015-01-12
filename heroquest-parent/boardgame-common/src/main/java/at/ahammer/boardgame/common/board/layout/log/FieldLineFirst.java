package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The first line of a {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}.
 */
public class FieldLineFirst extends FieldLine {

    public FieldLineFirst(Field field, int line) {
        super(field, line);
    }

    @Override
    public String text(int line, Field field, StringUtil stringUtil) {
        return stringUtil.repeatedString(defaultChar(), getMaxInnerWidth());
    }

    @Override
    public char firstChar() {
        return '+';
    }

    @Override
    public char defaultChar() {
        return '-';
    }

    @Override
    public char lastChar() {
        return '+';
    }
}
