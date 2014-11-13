package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class that holds all activated alternatives.
 *
 * @see at.ahammer.boardgame.cdi.AlternativeInGameContext
 */
@ApplicationScoped
public class AlternativesInGameContext {

    /**
     * All activated alternatives.
     */
    private final Set<Class<?>> alternatives = new HashSet<>();

    public Set<Class<?>> getAlternatives() {
        return Collections.unmodifiableSet(alternatives);
    }

    public void clear() {
        alternatives.clear();
    }

    public void add(Class<?> alternative) {
        alternatives.add(alternative);
    }

    public void addAll(Collection<Class<?>> alternatives) {
        this.alternatives.addAll(alternatives);
    }

    public boolean contains(Class<?> clazz) {
        boolean contains = false;
        for (Class<?> currentClazz : alternatives) {
            if (clazz.isAssignableFrom(currentClazz)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
