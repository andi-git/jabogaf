package org.jabogaf.core.state;

import org.jabogaf.api.event.GameStateChangedEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * An abstract class for a value that is cached until the game-state changed (via {@link GameStateChangedEvent} where the
 * cached value is a {@link Map}. To create the cache a parameter is needed.
 */
public abstract class CachedValueMap<VALUE, PARAM> extends CachedValue<VALUE> {

    private Map<PARAM, VALUE> map = new HashMap<>();

    /**
     * Get the value. If the cache is valid, the cached value will be returned. If the cache is not valid, the value
     * will be created.
     *
     * @param param the input-parameters
     * @return the value
     */
    public VALUE get(PARAM param) {
        if (!map.containsKey(param)) {
            map.put(param, create().apply(param));
        }
        return map.get(param);
    }

    @Override
    public void invalidateCache() {
        System.out.println("--> invalidate cache");
        super.invalidateCache();
        map.clear();
    }

    protected abstract Function<PARAM, VALUE> create();

    public Map<PARAM, VALUE> getMap() {
        return Collections.unmodifiableMap(map);
    }
}
