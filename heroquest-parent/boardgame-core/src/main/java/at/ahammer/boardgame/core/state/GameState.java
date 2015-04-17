package at.ahammer.boardgame.core.state;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * The basic class for the state of the game. When getting all instances of this type at runtime, the complete game-state is available, e.g. to persist.
 * <p/>
 * When a method is called to mutate a value, an event {@link GameStateChanged} is called (via {@link SetterFiresGameStateChanged}).
 * <p/>
 * So the attributes (values) of a {@link GameContextBean} are hold in a separate cdi-bean. This is necessary, because a {@link GameContextBean} is not a cdi-bean, so some cdi-interceptors like interceptors (e.g. the {@link SetterFiresGameStateChanged}) are not working in the {@link GameContextBean}.
 */
@SetterFiresGameStateChanged
public abstract class GameState {

}
