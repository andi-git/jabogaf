package org.jabogaf.api.loader;

import java.lang.reflect.Array;

/**
 * FIXME: the strategy of 'ServiceLoader' is no longer used -> delete this class and the resources!
 */
public class ServiceLoader {

    public static <T> T get(Class<T> type) {
        final T[] instance = (T[]) Array.newInstance(type, 1);
        java.util.ServiceLoader<T> instanceServiceLoader = java.util.ServiceLoader.load(type);
        instanceServiceLoader.forEach(g -> instance[0] = g);
        if (instance[0] == null) {
            throw new RuntimeException("unable to get instance of " + type + " via " + java.util.ServiceLoader.class);
        }
        return instance[0];
    }
}
