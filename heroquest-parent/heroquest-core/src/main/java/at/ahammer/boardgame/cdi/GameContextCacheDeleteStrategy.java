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

    public Set<UUID> getContextsToDelete(Map<UUID, GameContextInstance> gameContextCache) {
        final Set<UUID> toDelete = new HashSet<>();
        Instant now = Instant.now();
        gameContextCache.forEach((gameContextId, gameContextInstanceTmp) -> {
            if (Duration.between(now, gameContextInstanceTmp.getLastAccess()).getSeconds() > (60 * 60 * 6)) {
                toDelete.add(gameContextId);
            }
        });
        return toDelete;
    }
}
