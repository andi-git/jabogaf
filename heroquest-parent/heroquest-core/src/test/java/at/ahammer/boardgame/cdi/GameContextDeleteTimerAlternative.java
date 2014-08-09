package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 * Created by andreas on 09.08.14.
 */
@ApplicationScoped
@Alternative
public class GameContextDeleteTimerAlternative extends GameContextDeleteTimer {

    @Override
    public boolean shouldCheck() {
        return shouldCheck(1);
    }
}
