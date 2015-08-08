package org.jabogaf.core.state;

import org.jabogaf.api.state.ChangesGameState;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

/**
 * A helper class that can check, if a method changes the game-state. Default-Conditions are:
 *
 * <ul>
 *     <li>prefix of method-name is {@code set}</li>
 *     <li>prefix of method-name is {@code add}</li>
 *     <li>prefix of method-name is {@code remove}</li>
 *     <li>prefix of method-name is {@code clear}</li>
 *     <li>method is annotated with {@link ChangesGameState}</li>
 * </ul>
 */
@ApplicationScoped
public class MutableMethods {

    private final List<String> mutableMethodPrefixes = new ArrayList<>();

    private final Set<Method> methodCache = new HashSet<>();

    @PostConstruct
    private void init() {
        addDefaults();
    }

    public void addDefaults() {
        clear();
        mutableMethodPrefixes.add("set");
        mutableMethodPrefixes.add("add");
        mutableMethodPrefixes.add("remove");
        mutableMethodPrefixes.add("clear");
    }

    public void add(String startsWith) {
        mutableMethodPrefixes.add(startsWith);
    }

    public void clear() {
        mutableMethodPrefixes.clear();
    }

    public List<String> getMutableMethodPrefixes() {
        return Collections.unmodifiableList(mutableMethodPrefixes);
    }

    public boolean isMutableMethod(Method method) {
        if (methodCache.contains(method)) {
            return true;
        }
        if (method != null) {
            if (hasChangesGameStateAnnotation(method)) {
                methodCache.add(method);
                return true;
            } else {
                for (String prefix : mutableMethodPrefixes) {
                    if (startsWithPrefix(method, prefix)) {
                        methodCache.add(method);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasChangesGameStateAnnotation(Method method) {
        return Stream.of(method.getDeclaredAnnotations()).
                map(Annotation::annotationType).
                anyMatch((c) -> c == ChangesGameState.class);
    }

    private boolean startsWithPrefix(Method method, String prefix) {
        return method.getName().startsWith(prefix);
    }
}
