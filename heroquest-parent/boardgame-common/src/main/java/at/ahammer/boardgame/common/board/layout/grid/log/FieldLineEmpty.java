package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

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

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineEmpty> {

        @Override
        public int rank() {
            return 90;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            return isPreLastElement(state);
        }

        @Override
        public FieldLineEmpty create(FactoryForFieldLines.State state) {
            return new FieldLineEmpty(state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return false;
        }
    }
}
