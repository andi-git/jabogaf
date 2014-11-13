package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ahammer on 06.08.2014.
 */
@ApplicationScoped
public class GameContextCacheDeleteStrategy {

    private static final int MAX_UNUSED_DURATION = 60 * 60 * 6;

    public Set<UUID> getContextsToDelete(Map<UUID, GameContextInstance> gameContextCache, UUID currentId) {
        return getContextsToDelete(gameContextCache, currentId, MAX_UNUSED_DURATION);
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

}
