package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.event.GameStateChangedCollector;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GameStateChangedCollectorBasic implements GameStateChangedCollector {

    /**
     * Get the {@link List} of {@link GameStateChanged} from a producer method in a certain cdi-context
     */
    @Inject
    private List<GameStateChanged> gameStateChangesInSpecifiedContext;

    @Override
    public List<GameStateChanged> getGameStateChanges() {
        return Collections.unmodifiableList(gameStateChangesInSpecifiedContext);
    }

    private void observeGameStateChangedValueEvent(@Observes GameStateChangedValue gameStateChanged) {
        gameStateChangesInSpecifiedContext.add(new GameStateChangedValue(gameStateChanged));
    }

    private void observeGameStateChangedBeanEvent(@Observes GameStateChangedBean gameStateChanged) {
        gameStateChangesInSpecifiedContext.add(new GameStateChangedBean(gameStateChanged));
    }
}
