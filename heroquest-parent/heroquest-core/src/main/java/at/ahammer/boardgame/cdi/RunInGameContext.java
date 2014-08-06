package at.ahammer.boardgame.cdi;

import java.util.UUID;

/**
 * Created by ahammer on 06.08.2014.
 */
@FunctionalInterface
public interface RunInGameContext<T> {

    T call(UUID gameContextId) throws Throwable;
}
