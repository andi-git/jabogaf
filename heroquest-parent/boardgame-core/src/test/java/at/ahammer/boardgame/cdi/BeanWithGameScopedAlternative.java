package at.ahammer.boardgame.cdi;

import javax.annotation.PostConstruct;

@GameScoped
@AlternativeInGameContext
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "i'm an ALTERNATIVE in GameContext";
    }
}
