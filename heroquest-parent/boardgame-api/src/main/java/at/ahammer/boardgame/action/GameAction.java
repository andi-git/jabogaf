package at.ahammer.boardgame.action;

/**
 * This is an action that can be performed in a game.
 * <p/>
 * This component doesn't have a state and can be {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface GameAction<T extends GameActionParameter> {

    /**
     * Perform a {@link at.ahammer.boardgame.action.GameAction}
     *
     * @param parameter the parameter(s) for the {@link at.ahammer.boardgame.action.GameAction}
     */
    void perform(T parameter) throws Exception;
}
