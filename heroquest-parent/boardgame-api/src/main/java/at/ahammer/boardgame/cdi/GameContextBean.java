package at.ahammer.boardgame.cdi;

import java.util.ServiceLoader;

/**
 * This is the superclass for all new instantiated beans within the {@link at.ahammer.boardgame.cdi.GameScoped}. This
 * class only provides the method getId() as abstract method and it's not an obligation to use this class as
 * superclass.
 */
public abstract class GameContextBean {

    private final String id;

    /**
     * Create a new {@link at.ahammer.boardgame.cdi.GameContextBean}. The bean will also be registered in GameContext
     * via {@link at.ahammer.boardgame.cdi.GameContextManager}.
     *
     * @param id the id of the {@link at.ahammer.boardgame.cdi.GameContextBean}.
     */
    protected GameContextBean(String id) {
        this.id = id;
        getGameContextManager().resolve(this);
    }

    public String getId() {
        return id;
    }

    /**
     * Get the {@link at.ahammer.boardgame.cdi.GameContextManager} via the {@link java.util.ServiceLoader}.
     *
     * @return the current {@link at.ahammer.boardgame.cdi.GameContextManager} or a {@link java.lang.RuntimeException}
     * will be thrown
     */
    private GameContextManager getGameContextManager() {
        final GameContextManager[] gameContextManager = {null};
        ServiceLoader<GameContextManager> gameContextManagerServiceLoader = ServiceLoader.load(GameContextManager.class);
        gameContextManagerServiceLoader.forEach(g -> gameContextManager[0] = g);
        if (gameContextManager[0] == null) {
            throw new RuntimeException("unable to get instance of " + GameContextManager.class + " via " + ServiceLoader.class);
        }
        return gameContextManager[0];
    }
}
