package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.util.string.StringUtil;

import javax.enterprise.context.ApplicationScoped;

/**
 * The line to add the name of a {@link org.jabogaf.api.board.field.Field}.
 */
@ApplicationScoped
public class FieldLineFieldName extends FieldLine<FieldLineFieldName.Representation> {

    @Override
    public int rank() {
        return 20;
    }

    @Override
    public boolean isLast(FactoryForFieldLines.State state) {
        return true;
    }

    @Override
    public FieldLineFieldName.Representation create(FactoryForFieldLines.State state) {
        return new FieldLineFieldName.Representation(state.getField(), state.getStringUtil());
    }

    @Override
    public boolean atLeastOnce() {
        return true;
    }

    public static class Representation extends FieldLine.Representation {

        private final Field field;

        public Representation(Field field, StringUtil stringUtil) {
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
}
