package at.ahammer.boardgame.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by andreas on 09.08.14.
 */
@ApplicationScoped
@Alternative
public class GameContextCacheDeleteStrategyAlternative extends GameContextCacheDeleteStrategy {

    @Override
    public Set<UUID> getContextsToDelete(Map<UUID, GameContextInstance> gameContextCache, UUID currentId) {
        return getContextsToDelete(gameContextCache, currentId, 1);
    }
}
