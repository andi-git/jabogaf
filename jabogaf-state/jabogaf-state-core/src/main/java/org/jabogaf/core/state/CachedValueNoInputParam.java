package org.jabogaf.core.state;

import org.jabogaf.api.event.GameStateChanged;

import java.util.function.Supplier;

/**
 * An abstract class for a value that is cached until the game-state changed (via {@link GameStateChanged}. To
 * fill the cache no input parameter is needed.
 */
public abstract class CachedValueNoInputParam<VALUE> extends CachedValue<VALUE> {

    /**
     * Get the value. If the cache is valid, the cached value will be returned. If the cache is not valid, the value
     * will be created.
     * will be created.
     *
     * @return the value
     */
    public VALUE get() {
        return getCached(() -> create().get(), true);
    }

    protected abstract Supplier<VALUE> create();
}
