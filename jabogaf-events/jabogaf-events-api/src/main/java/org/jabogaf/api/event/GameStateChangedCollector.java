package org.jabogaf.api.event;

import java.util.List;

/**
 * Collect and holds all changes of {@code GameState} instances in the correct order. So the complete change of the
 * game-state can be reproduced.
 */
public interface GameStateChangedCollector {

    List<GameStateChanged> getGameStateChanges();
}
