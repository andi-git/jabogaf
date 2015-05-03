package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * The last line of a {@link FieldLine}.
 */
@ApplicationScoped
public class FieldLineLast extends FieldLine {

    @Override
    public int rank() {
        return 100;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        return true;
    }

    @Override
    public FieldLineLast.Representation create(FactoryForFieldLines.State state) {
        return new FieldLineLast.Representation(state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return true;
    }

    public static class Representation extends FieldLine.Representation {

        public Representation(StringUtil stringUtil) {
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
}
