package org.jabogaf.common.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.layout.KeyTwoFields;

import static com.google.common.base.Preconditions.checkNotNull;

public class KeyTwoFieldsBasic implements KeyTwoFields {

    private final Field from;

    private final Field to;

    public KeyTwoFieldsBasic(Field from, Field to) {
        checkNotNull(to);
        checkNotNull(from);
        this.to = to;
        this.from = from;
    }

    @Override
    public Field getFrom() {
        return from;
    }

    @Override
    public Field getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyTwoFieldsBasic that = (KeyTwoFieldsBasic) o;
        return from.equals(that.from) && to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "KeyTwoFieldsBasic{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
