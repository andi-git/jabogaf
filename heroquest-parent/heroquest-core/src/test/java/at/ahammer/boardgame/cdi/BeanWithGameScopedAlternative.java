package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.Alternative;

/**
 * Created by andreas on 08.08.14.
 */
@Alternative
public class BeanWithGameScopedAlternative extends BeanWithGameScoped {

    @Override
    public String getString() {
        return "alternative";
    }
}
