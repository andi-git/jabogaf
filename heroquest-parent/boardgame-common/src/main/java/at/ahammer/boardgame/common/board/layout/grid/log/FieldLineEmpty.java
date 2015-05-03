package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * An empty line of a {@link FieldLine}.
 */
@ApplicationScoped
public class FieldLineEmpty extends FieldLine<FieldLineEmpty.Representation> {

    @Override
    public int rank() {
        return 90;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        return isPreLastElement(state);
    }

    @Override
    public Representation create(FactoryForFieldLines.State state) {
        return new Representation(state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return false;
    }

    public static class Representation extends FieldLine.Representation {

        public Representation(StringUtil stringUtil) {
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
}
