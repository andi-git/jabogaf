package org.jabogaf.api.event;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Collect and holds all changes of {@code GameState} instances in the correct order. So the complete change of the
 * game-state can be reproduced.
 */
public interface GameStateChangedEventCollector {

    /**
     * Get a {@link Map} of all {@link GameStateChangedEventGroup}s where the key is the {@link UUID} of the {@link
     * GameStateChangedEventGroup}.
     *
     * @return
     */
    Map<UUID, GameStateChangedEventGroup> getGameStateChangedEventGroups();

    /**
     * Get {@link List} of all {@link GameStateChangedEventGroup}s ordered by there {@link
     * GameStateChangedEventGroup#creationTime()}.
     *
     * @return
     */
    List<GameStateChangedEventGroup> getGameStateChangedEventGroupsOrderedByTime();
}
