package org.jabogaf.core.event;

import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.api.event.GameStateChanged;
import org.jabogaf.api.state.GameState;

import java.time.Instant;

/**
 * The game-state-event when a new bean in game-state was created.
 */
public class GameStateChangedBean extends GameStateChanged {

    private final Class<?> type;

    private final String gameStateSerialized;

    public GameStateChangedBean(Instant time, GameContextBean gameContextBean) {
        this(time, gameContextBean.getClass(), gameContextBean.getState());
    }

    public GameStateChangedBean(GameStateChangedBean gameStateChangeBean) {
        super(gameStateChangeBean.getTime());
        this.type = gameStateChangeBean.getType();
        this.gameStateSerialized = gameStateChangeBean.getGameStateSerialized();
    }

    public GameStateChangedBean(Instant time, Class<?> type, GameState state) {
        super(time);
        this.type = type;
        this.gameStateSerialized = state.serialize();
    }

    public Class<?> getType() {
        return type;
    }

    public String getGameStateSerialized() {
        return gameStateSerialized;
    }
}
