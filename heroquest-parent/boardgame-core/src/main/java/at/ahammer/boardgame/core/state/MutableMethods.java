package at.ahammer.boardgame.core.state;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class MutableMethods {

    private final List<String> mutableMethodPrefixes = new ArrayList<>();

    @PostConstruct
    private void init() {
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
        for (String prefix : mutableMethodPrefixes) {
            if (method.getName().startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
