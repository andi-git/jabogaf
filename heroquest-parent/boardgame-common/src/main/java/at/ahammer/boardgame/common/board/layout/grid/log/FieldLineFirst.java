package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * The first line of a {@link FieldLine}.
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

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineFirst> {

        @Override
        public int rank() {
            return 10;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            return true;
        }

        @Override
        public FieldLineFirst create(FactoryForFieldLines.State state) {
            return new FieldLineFirst(state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return true;
        }
    }

}
