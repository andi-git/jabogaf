package org.jabogaf.util.loader;

import java.lang.reflect.Array;

/**
 * Load a concrete class of an interface via the {@link java.util.ServiceLoader}.
 */
public class ServiceLoader {

    /**
     * Load a concrete class of an interface via the {@link java.util.ServiceLoader} or throw a {@link RuntimeException}
     * if this is not possible.
     *
     * @param type the type of the interface to load
     * @param <T>  the generic type
     * @return the conrete instance loaded via {@link java.util.ServiceLoader}
     */
    public static <T> T load(Class<T> type) {
        @SuppressWarnings("unchecked") final T[] instance = (T[]) Array.newInstance(type, 1);
        java.util.ServiceLoader<T> instanceServiceLoader = java.util.ServiceLoader.load(type);
        instanceServiceLoader.forEach(g -> instance[0] = g);
        if (instance[0] == null) {
            throw new RuntimeException("unable to get instance of " + type + " via " + java.util.ServiceLoader.class);
        }
        return instance[0];
    }
}
