package at.ahammer.boardgame.action;

/**
 * This interface encapsulates the code which should be executed when a {@link at.ahammer.boardgame.action.GameAction} is performed.
 */
@FunctionalInterface
public interface ActionPerform {

    /**
     * Run the code that should be executed when a {@link at.ahammer.boardgame.action.GameAction} is performed.
     *
     * @throws Exception
     */
    void perform() throws Exception;
}
