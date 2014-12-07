package at.ahammer.boardgame.api.loader;

import at.ahammer.boardgame.api.cdi.GameContextManager;

import java.lang.reflect.Array;

public class ServiceLoader {

    public static <T> T get(Class<T> type) {
        final T[] instance = (T[]) Array.newInstance(type, 1);
//        final T[] instance = {null};
        java.util.ServiceLoader<T> instanceServiceLoader = java.util.ServiceLoader.load(type);
        instanceServiceLoader.forEach(g -> instance[0] = g);
        if (instance[0] == null) {
            throw new RuntimeException("unable to get instance of " + type + " via " + java.util.ServiceLoader.class);
        }
        return instance[0];
    }

}
