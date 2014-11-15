package at.ahammer.boardgame.api.action;

/**
 * This is an action that can be performed in a game.
 * <p/>
 * This component doesn't have a state and can be {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface GameAction<T extends GameActionParameter> {

    /**
     * Perform a {@link GameAction}
     *
     * @param parameter the parameter(s) for the {@link GameAction}
     */
    void perform(T parameter) throws Exception;
}
