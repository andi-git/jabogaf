package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

/**
 * The last line of a {@link FieldLine}.
 */
public class FieldLineLast extends FieldLine {

    public FieldLineLast(StringUtil stringUtil) {
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
