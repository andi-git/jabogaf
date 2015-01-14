package at.ahammer.boardgame.api.object;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * An object in the game, e.g. door, wall,...
 */
public class GameObject extends GameContextBean {

    private boolean visible = false;

    /**
     * Create a new {@link GameObject} with an id.
     *
     * @param id the id of the {@link GameObject}
     */
    public GameObject(String id) {
        super(id);
    }

    /**
     * Check if the current {@link GameObject} is visible for the players.
     *
     * @return {@code true} if the field is visible for the players
     */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void detected() {
        this.visible = true;
    }
}
