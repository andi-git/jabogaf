package org.jabogaf.core.util;

import javax.enterprise.inject.Typed;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Typed()
public class CacheForString<T> {

    private final Map<String, T> cache = new HashMap<>();

    public T get(String key, Supplier<T> createElement) {
        if (!cache.containsKey(key)) {
            cache.put(key, createElement.get());
        }
        return cache.get(key);
    }
}
