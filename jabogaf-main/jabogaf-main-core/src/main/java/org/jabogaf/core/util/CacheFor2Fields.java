package org.jabogaf.core.util;

import org.jabogaf.api.board.field.Field;

import javax.enterprise.inject.Typed;
import java.util.function.Supplier;

@Typed()
public class CacheFor2Fields<T> {

    private CacheForString<T> cacheForString = new CacheForString<>();

    private final KeyFor2FieldsCreator keyFor2FieldsCreator = new KeyFor2FieldsCreator();

    public T get(Field field, Supplier<T> createElement) {
        return cacheForString.get(field.getId(), createElement);
    }

    public T get(Field field1, Field field2, Supplier<T> createElement) {
        return cacheForString.get(keyFor2FieldsCreator.generateKey(field1, field2), createElement);
    }
}
