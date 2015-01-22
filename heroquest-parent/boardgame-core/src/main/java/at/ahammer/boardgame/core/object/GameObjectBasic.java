package at.ahammer.boardgame.core.object;

import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;

public class GameObjectBasic extends GameContextBeanBasic implements GameObject {

    private boolean visible = false;

    /**
     * Create a new {@link at.ahammer.boardgame.core.object.GameObjectBasic} with an id.
     *
     * @param id the id of the {@link at.ahammer.boardgame.core.object.GameObjectBasic}
     */
    public GameObjectBasic(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void detected() {
        this.visible = true;
    }
}
