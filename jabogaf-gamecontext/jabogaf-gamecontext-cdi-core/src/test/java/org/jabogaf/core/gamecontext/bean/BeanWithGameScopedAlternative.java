package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.gamecontext.AlternativeInGameContext;
import org.jabogaf.api.gamecontext.GameScoped;

@GameScoped
@AlternativeInGameContext
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "i'm an ALTERNATIVE in GameContext";
    }
}
