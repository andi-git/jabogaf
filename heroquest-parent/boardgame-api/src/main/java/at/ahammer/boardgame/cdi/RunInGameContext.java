package at.ahammer.boardgame.cdi;

import java.util.UUID;

/**
 * Run the code within a new {@link at.ahammer.boardgame.cdi.GameScoped}.
 */
@FunctionalInterface
public interface RunInGameContext<T> {

    /**
     * Run the assigned code within a new {@link at.ahammer.boardgame.cdi.GameScoped}
     *
     * @param gameContextId the id of the context
     * @return the return-value
     * @throws Throwable
     */
    T call(UUID gameContextId) throws Throwable;
}
