package org.jabogaf.core.util;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.core.state.CachedValue;

/**
 * A class that can be used as parameter (with two fields) for caching via {@link CachedValue}
 */
public class ParameterForCacheOfTwoFields {

    private final Field position;

    private final Field target;

    public ParameterForCacheOfTwoFields(Field position, Field target) {
        this.position = position;
        this.target = target;
    }

    public Field getPosition() {
        return position;
    }

    public Field getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterForCacheOfTwoFields parameter = (ParameterForCacheOfTwoFields) o;
        return position.equals(parameter.position) && target.equals(parameter.target);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "position=" + position +
                ", target=" + target +
                '}';
    }
}
