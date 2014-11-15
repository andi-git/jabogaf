package at.ahammer.boardgame.api.cdi;

import java.util.ServiceLoader;
import java.util.UUID;

/**
 * This is the superclass for all new instantiated beans within the {@link GameScoped}. This
 * class only provides the method getId() as abstract method and it's not an obligation to use this class as
 * superclass.
 */
public abstract class GameContextBean {

    private final String id;

    /**
     * Create a new {@link GameContextBean} with a random id. The bean will also be registered in GameContext
     * via {@link GameContextManager}.
     *
     * @see #GameContextBean(String)
     */
    protected GameContextBean() {
        this(UUID.randomUUID().toString());
    }

    /**
     * Create a new {@link GameContextBean}. The bean will also be registered in GameContext
     * via {@link GameContextManager}.
     *
     * @param id the id of the {@link GameContextBean}.
     */
    protected GameContextBean(String id) {
        this.id = id;
        getGameContextManager().add(this, id);
    }

    public String getId() {
        return id;
    }

    /**
     * Get the {@link GameContextManager} via the {@link java.util.ServiceLoader}.
     *
     * @return the current {@link GameContextManager} or a {@link java.lang.RuntimeException}
     * will be thrown
     */
    protected GameContextManager getGameContextManager() {
        final GameContextManager[] gameContextManager = {null};
        ServiceLoader<GameContextManager> gameContextManagerServiceLoader = ServiceLoader.load(GameContextManager.class);
        gameContextManagerServiceLoader.forEach(g -> gameContextManager[0] = g);
        if (gameContextManager[0] == null) {
            throw new RuntimeException("unable to get instance of " + GameContextManager.class + " via " + ServiceLoader.class);
        }
        return gameContextManager[0];
    }
}
