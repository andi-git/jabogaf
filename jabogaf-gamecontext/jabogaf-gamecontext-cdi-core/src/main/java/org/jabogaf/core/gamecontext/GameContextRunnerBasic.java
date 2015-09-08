package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextRunner;
import org.jabogaf.api.gamecontext.RunInGameContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class GameContextRunnerBasic implements GameContextRunner {

    @Inject
    private GameContextCache gameContextCache;

    @Override
    public <T> T runInGameContext(UUID gameContextId, BeanManager beanManager, RunInGameContext<T> runnable) {
        return gameContextCache.runInGameContext(gameContextId, beanManager, runnable);
    }
}
