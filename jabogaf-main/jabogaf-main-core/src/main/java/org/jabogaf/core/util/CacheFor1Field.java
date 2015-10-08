package org.jabogaf.core.util;

import org.jabogaf.api.board.field.Field;

import javax.enterprise.inject.Typed;
import java.util.function.Supplier;

@Typed()
public class CacheFor1Field<T> {

    private CacheForString<T> cacheForString = new CacheForString<>();

    public T get(Field field, Supplier<T> createElement) {
        return cacheForString.get(field.getId(), createElement);
    }
}
