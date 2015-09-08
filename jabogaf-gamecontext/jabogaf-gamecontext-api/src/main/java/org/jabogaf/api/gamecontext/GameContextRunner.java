package org.jabogaf.api.gamecontext;

import javax.enterprise.inject.spi.BeanManager;
import java.util.UUID;

/**
 * Run code within a {@code GameContext}.
 */
public interface GameContextRunner {

    /**
     * Start a new {@code GameContext} or reuse an existing one via the {@code gameContextId}. The code within the
     * {@code runnable} will be executed within the {@code GameContext}.
     * <p>
     * This method will throw {@link Throwable}, because for JUnit-Tests.
     *
     * @param gameContextId the id (a {@link UUID} to reuse an existing {@link GameContextInstance}
     * @param beanManager   the current {@link BeanManager}
     * @param runnable      the code run within the {@code GameContext}
     * @param <T>           the return-type
     * @return the id of the used {@link GameContextInstance}
     * @throws Throwable
     */
    <T> T runInGameContext(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable);
}
