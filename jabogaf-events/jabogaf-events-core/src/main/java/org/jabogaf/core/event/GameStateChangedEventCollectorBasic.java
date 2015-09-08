package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEventCollector;
import org.jabogaf.api.event.GameStateChangedEventGroup;
import org.jabogaf.api.event.GameStateChangedEventGroupHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameStateChangedEventCollectorBasic implements GameStateChangedEventCollector {

    /**
     * Get the {@link Map} of {@link GameStateChangedEventGroup} from a producer method in a certain cdi-context
     */
    @Inject
    private Map<UUID, GameStateChangedEventGroup> gameStateChanges;

    @Inject
    private GameStateChangedEventGroupHandler gameStateChangedEventGroupHandler;

    @Override
    public Map<UUID, GameStateChangedEventGroup> getGameStateChangedEventGroups() {
        return Collections.unmodifiableMap(gameStateChanges);
    }

    @Override
    public List<GameStateChangedEventGroup> getGameStateChangedEventGroupsOrderedByTime() {
        return Collections.unmodifiableList(
                gameStateChanges.values().stream().
                        sorted((g1, g2) -> g1.creationTime().compareTo(g2.creationTime())).
                        collect(Collectors.toList()));
    }

    private void observeGameStateChangedValueEvent(@Observes GameStateChangedEventValue gameStateChanged) {
        getGameStateChangedEventGroup().addEvent(new GameStateChangedEventValue(gameStateChanged));
    }

    private void observeGameStateChangedBeanEvent(@Observes GameStateChangedEventBean gameStateChanged) {
        getGameStateChangedEventGroup().addEvent(new GameStateChangedEventBean(gameStateChanged));
    }

    private GameStateChangedEventGroup getGameStateChangedEventGroup() {
        GameStateChangedEventGroup currentGameStateChangedEventGroup = gameStateChangedEventGroupHandler.getCurrentGameStateChangedEventGroup();
        if (gameStateChanges.containsKey(currentGameStateChangedEventGroup.getUUID())) {
            return gameStateChanges.get(currentGameStateChangedEventGroup.getUUID());
        } else {
            gameStateChanges.put(currentGameStateChangedEventGroup.getUUID(), currentGameStateChangedEventGroup);
            return currentGameStateChangedEventGroup;
        }
    }
}
