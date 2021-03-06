package org.jabogaf.core.state;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateInvalidEvent;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import java.time.Instant;
import java.util.concurrent.Callable;

/**
 * This is the abstract class for a value that is cached until the game-state changed (via {@link GameStateChangedEvent}).
 * The creation of the value must be specified in the concrete implementation of the class.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class CachedValue<VALUE> {

    private boolean valid = false;

    private Instant lastEventChange;

    private VALUE value;

    @PostConstruct
    private void init() {
        lastEventChange = Instant.now();
    }

    protected VALUE getCached(Callable<VALUE> callable, boolean useCache) {
        if (!useCache || !valid) {
            value = null;
            try {
                value = callable.call();
                valid = true;
            } catch (Exception e) {
                log().error(e.getMessage());
                valid = false;
            }
        }
        return value;
    }

    protected abstract Logger log();

    public void invalidateCache() {
        valid = false;
    }

    /**
     * Check if the cache is valid.
     *
     * @return {@code true} if the cache is valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Get the timestamp when the last {@link GameStateChangedEvent} was observed.
     *
     * @return the timestamp when the last {@link GameStateChangedEvent} was observed
     */
    public Instant getLastEventChange() {
        return lastEventChange;
    }

    private void observeGameStateChange(@Observes GameStateChangedEvent gameStateChangedEvent) {
        log().debug("observe {}", GameStateChangedEvent.class.getSimpleName());
        invalidateCache();
        lastEventChange = Instant.now();
    }

    private void objectGameStateInvalid(@Observes GameStateInvalidEvent gameStateInvalidEvent) {
        invalidateCache();
    }
}
