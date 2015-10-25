package org.jabogaf.api.object;

import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;

/**
 * An object in the game, e.g. door, wall,...
 */
public interface GameObject<POSITION extends ContainsGameObjects> extends LayoutActionImpact<GameObject, POSITION> {

    /**
     * Check if the current {@link GameObject} is visible for the players.
     *
     * @return {@code true} if the field is visible for the players
     */
    boolean isVisible();

    void setVisible(boolean visible);

    void detected();
}
