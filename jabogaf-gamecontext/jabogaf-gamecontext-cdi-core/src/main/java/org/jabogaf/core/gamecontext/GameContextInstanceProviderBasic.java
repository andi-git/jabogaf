package org.jabogaf.core.gamecontext;

import org.jabogaf.api.gamecontext.GameContextInstance;
import org.jabogaf.api.gamecontext.GameContextInstanceProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GameContextInstanceProviderBasic implements GameContextInstanceProvider {

    @Inject
    private GameContextCache gameContextCache;

    @Override
    public GameContextInstance getCurrentGameContextInstance() {
        return gameContextCache.getCurrentGameContextInstance();
//        return new GameContextCache().getCurrentGameContextInstance();
    }
}
