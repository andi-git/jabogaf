package org.jabogaf.api.state;

import org.jabogaf.api.event.GameStateChanged;

import java.util.List;

/**
 * Collect and holds all changes of {@link GameState} instances in the correct order. So the complete change of the
 * game-state can be reproduced.
 */
public interface GameStateChangedCollector {

    List<GameStateChanged> getGameStateChanges();
}
