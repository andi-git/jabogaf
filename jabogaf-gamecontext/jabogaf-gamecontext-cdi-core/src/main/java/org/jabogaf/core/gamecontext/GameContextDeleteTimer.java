package org.jabogaf.core.gamecontext;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;

@ApplicationScoped
public class GameContextDeleteTimer {

    private static final int DURATION = 60;

    private int currentDuration = DURATION;

    private Instant lastCheck = Instant.now();

    public boolean shouldCheck() {
        return shouldCheck(currentDuration);
    }

    protected boolean shouldCheck(int duration) {
        Instant now = Instant.now();
        if (Duration.between(lastCheck, now).getSeconds() > duration) {
            lastCheck = now;
            return true;
        }
        return false;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    public void setCurrentDuration(int currentDuration) {
        this.currentDuration = currentDuration;
    }

    public void resetDuration() {
        this.currentDuration = DURATION;
    }
}
