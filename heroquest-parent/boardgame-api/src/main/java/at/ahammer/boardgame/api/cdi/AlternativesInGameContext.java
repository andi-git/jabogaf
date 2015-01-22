package at.ahammer.boardgame.api.cdi;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class that holds all activated alternatives.
 *
 * @see AlternativeInGameContext
 */
public interface AlternativesInGameContext {

    Set<Class<?>> getAlternatives();

    void clear();

    void add(Class<?> alternative);

    void addAll(Collection<Class<?>> alternatives);

    boolean contains(Class<?> clazz);
}
