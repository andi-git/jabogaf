package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by andreas on 07.08.14.
 */
@ApplicationScoped
public class GameContextDeleteTimer {

    private static final int DURATION = 60;

    private Instant lastCheck = Instant.now();

    public boolean shouldCheck() {
        return shouldCheck(DURATION);
    }

    protected boolean shouldCheck(int duration) {
        Instant now = Instant.now();
        if (Duration.between(lastCheck, now).getSeconds() > duration) {
            lastCheck = now;
            return true;
        }
        return false;

    }
}
