package org.jabogaf.api.gamecontext;

import java.util.UUID;

/**
 * Run the code within a new game-context.
 */
@FunctionalInterface
public interface RunInGameContext<T> {

    /**
     * Run the assigned code within a new game-context
     *
     * @param gameContextId the id of the context
     * @return the return-value
     * @throws Throwable
     */
    T call(UUID gameContextId) throws Throwable;
}
