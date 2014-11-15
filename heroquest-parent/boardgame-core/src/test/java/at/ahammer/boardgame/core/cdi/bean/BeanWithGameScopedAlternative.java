package at.ahammer.boardgame.core.cdi.bean;

import at.ahammer.boardgame.api.cdi.AlternativeInGameContext;
import at.ahammer.boardgame.api.cdi.GameScoped;

@GameScoped
@AlternativeInGameContext
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "i'm an ALTERNATIVE in GameContext";
    }
}
