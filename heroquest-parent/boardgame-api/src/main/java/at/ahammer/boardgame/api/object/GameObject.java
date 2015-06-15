package at.ahammer.boardgame.api.object;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.resource.Resource;

/**
 * An object in the game, e.g. door, wall,...
 */
public interface GameObject<POSITION> extends GameContextBean {

    /**
     * Check if the current {@link GameObject} is visible for the players.
     *
     * @return {@code true} if the field is visible for the players
     */
    boolean isVisible();

    void setVisible(boolean visible);

    void detected();

    POSITION getPosition();

    Resource movementCost();
}
