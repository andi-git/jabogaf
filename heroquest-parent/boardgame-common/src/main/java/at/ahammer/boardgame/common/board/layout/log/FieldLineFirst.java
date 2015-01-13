package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.util.string.StringUtil;

/**
 * The first line of a {@link at.ahammer.boardgame.common.board.layout.log.FieldLine}.
 */
public class FieldLineFirst extends FieldLine {

    public FieldLineFirst(StringUtil stringUtil) {
        super(stringUtil);
    }

    @Override
    public String text() {
        return getStringUtil().repeatedString('-', getMaxInnerWidth());
    }

    @Override
    public char firstChar() {
        return '+';
    }

    @Override
    public char lastChar() {
        return '+';
    }
}
