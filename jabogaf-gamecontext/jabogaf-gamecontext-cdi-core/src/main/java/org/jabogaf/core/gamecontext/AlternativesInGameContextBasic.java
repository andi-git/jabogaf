package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.AlternativesInGameContext;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AlternativesInGameContextBasic implements AlternativesInGameContext {

    /**
     * All activated alternatives.
     */
    private final Set<Class<?>> alternatives = new HashSet<>();

    @Override
    public Set<Class<?>> getAlternatives() {
        return Collections.unmodifiableSet(alternatives);
    }

    @Override
    public void clear() {
        alternatives.clear();
    }

    @Override
    public void add(Class<?> alternative) {
        alternatives.add(alternative);
    }

    @Override
    public void addAll(Collection<Class<?>> alternatives) {
        this.alternatives.addAll(alternatives);
    }

    @Override
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
