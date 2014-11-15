package at.ahammer.boardgame.core.cdi;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class GameContextCacheDeleteStrategy {

    private static final int MAX_UNUSED_DURATION = 60 * 60 * 6;

    private int unusedDuration = MAX_UNUSED_DURATION;

    public Set<UUID> getContextsToDelete(Map<UUID, GameContextInstance> gameContextCache, UUID currentId) {
        return getContextsToDelete(gameContextCache, currentId, unusedDuration);
    }

    protected Set<UUID> getContextsToDelete(Map<UUID, GameContextInstance> gameContextCache, UUID currentId, int duration) {
        final Set<UUID> toDelete = new HashSet<>();
        gameContextCache.forEach((gameContextId, gameContextInstanceTmp) -> {
            if (gameContextId != currentId && Duration.between(gameContextInstanceTmp.getLastAccess(), Instant.now()).getSeconds() > (duration)) {
                toDelete.add(gameContextId);
            }
        });
        return toDelete;
    }

    public int getUnusedDuration() {
        return unusedDuration;
    }

    public void setUnusedDuration(int unusedDuration) {
        this.unusedDuration = unusedDuration;
    }

    public void resetUnusedDuration() {
        this.unusedDuration = MAX_UNUSED_DURATION;
    }
}
