package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.Alternative;

@Alternative
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "alternative";
    }
}
