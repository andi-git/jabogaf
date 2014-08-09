package at.ahammer.boardgame.test.util;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 08.08.14.
 */
public class AlternativesHolder {

    private static final Set<Class<?>> alternatives = new HashSet<>();

    public static Set<Class<?>> getAlternatives() {
        return alternatives;
    }

    public static void clear() {
        alternatives.clear();
    }

    public static void add(Class<?> alternative) {
        alternatives.add(alternative);
    }

}
