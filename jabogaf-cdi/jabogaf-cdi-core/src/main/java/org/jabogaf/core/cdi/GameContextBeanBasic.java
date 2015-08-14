package org.jabogaf.core.cdi;

import org.jabogaf.api.cdi.GameContextBean;
import org.jabogaf.api.cdi.GameContextManager;
import org.jabogaf.api.event.GameStateChanged;

import java.util.Random;
import java.util.UUID;

/**
 * This is the superclass for all new instantiated beans within the {@link org.jabogaf.api.cdi.GameScoped}.
 * This class only provides the method getId() as abstract method and it's not an obligation to use this class as
 * superclass.
 */
public abstract class GameContextBeanBasic<T extends GameContextBean> implements GameContextBean<T> {

    private final String id;

    private final GameContextManager gameContextManager;

    /**
     * Create a new {@link GameContextBeanBasic} with a random id. The bean will also be
     * registered in GameContext via {@link org.jabogaf.api.cdi.GameContextManager}.
     *
     * @see #GameContextBeanBasic(String)
     */
    protected GameContextBeanBasic() {
        this(UUID.randomUUID().toString());
    }

    /**
     * Create a new {@link GameContextBeanBasic}. The bean will also be registered in
     * GameContext via {@link GameContextManager}.
     * <p/>
     * Always fire an event of {@link GameStateChanged}.
     *
     * @param id the id of the {@link GameContextBeanBasic}.
     */
    protected GameContextBeanBasic(String id) {
        this.id = id;
        gameContextManager = new GameContextManagerBasic();
        gameContextManager.add(this, id);
        gameContextManager.fireGameStateChangedEvent(this);
    }

    @Override
    public String getId() {
        return id;
    }

    protected GameContextManager getGameContextManager() {
        return this.gameContextManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameContextBeanBasic that = (GameContextBeanBasic) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "GameContextBean{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(GameContextBean gameContextBean) {
        return this.getId().compareTo(gameContextBean.getId());
    }

    protected static String randomId() {
        return String.valueOf(System.nanoTime() + new Random().nextInt());
    }
}
