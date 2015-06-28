package org.jabogaf.core.cdi.bean;

import org.jabogaf.api.cdi.AlternativeInGameContext;
import org.jabogaf.api.cdi.GameScoped;

@GameScoped
@AlternativeInGameContext
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "i'm an ALTERNATIVE in GameContext";
    }
}
