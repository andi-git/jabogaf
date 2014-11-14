package at.ahammer.boardgame.cdi.bean;

import at.ahammer.boardgame.cdi.AlternativeInGameContext;
import at.ahammer.boardgame.cdi.GameScoped;

@GameScoped
@AlternativeInGameContext
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "i'm an ALTERNATIVE in GameContext";
    }
}
