package org.jabogaf.core.state;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.state.GameStateChangedCollector;

import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@GameScoped
public class GameStateChangedCollectorBasic implements GameStateChangedCollector {

    private List<GameStateChanged> gameStateChanges = new ArrayList<>();

    @Override
    public List<GameStateChanged> getGameStateChanges() {
        return Collections.unmodifiableList(gameStateChanges);
    }

    private void observeGameStateChangedEvent(@Observes GameStateChanged gameStateChanged) {
        gameStateChanges.add(new GameStateChanged(gameStateChanged));
    }
}
