package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.util.string.StringUtil;

/**
 * An empty line of a {@link FieldLine}.
 */
public class FieldLineEmpty extends FieldLine {

    public FieldLineEmpty(StringUtil stringUtil) {
        super(stringUtil);
    }

    @Override
    public String text() {
        return fixedLengthString(' ');
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
