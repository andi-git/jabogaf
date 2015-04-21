package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

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

    @ApplicationScoped
    public static class Usage extends FieldLineUsage<FieldLineFieldName> {

        @Override
        public int rank() {
            return 20;
        }

        @Override
        public boolean isLast(FactoryForFieldLines.State state) {
            return true;
        }

        @Override
        public FieldLineFieldName create(FactoryForFieldLines.State state) {
            return new FieldLineFieldName(state.getField(), state.getStringUtil());
        }

        @Override
        public boolean atLeastOnce() {
            return true;
        }
    }
}
