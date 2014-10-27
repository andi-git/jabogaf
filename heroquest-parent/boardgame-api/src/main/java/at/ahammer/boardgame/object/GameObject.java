package at.ahammer.boardgame.object;

import at.ahammer.boardgame.cdi.GameContextBean;

public abstract class GameObject extends GameContextBean {

    private boolean visible = false;

    /**
     * Create a new {@link at.ahammer.boardgame.object.GameObject} with an id.
     *
     * @param id the id of the {@link at.ahammer.boardgame.object.GameObject}
     */
    protected GameObject(String id) {
        super(id);
    }

    /**
     * Check if the current {@link at.ahammer.boardgame.object.GameObject} is visible for the players.
     *
     * @return {@code true} if the field is visible for the players
     */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
