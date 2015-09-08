package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.state.GameState;

import java.time.Instant;

/**
 * The game-state-event when a new bean in game-state was created.
 */
public class GameStateChangedEventBean extends GameStateChangedEvent {

    private final Class<?> type;

    private final String gameStateSerialized;

    public GameStateChangedEventBean(Instant time, GameContextBean gameContextBean) {
        this(time, gameContextBean.getClass(), gameContextBean.getState());
    }

    public GameStateChangedEventBean(GameStateChangedEventBean gameStateChangeBean) {
        super(gameStateChangeBean.getTime());
        this.type = gameStateChangeBean.getType();
        this.gameStateSerialized = gameStateChangeBean.getGameStateSerialized();
    }

    public GameStateChangedEventBean(Instant time, Class<?> type, GameState state) {
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
