package at.ahammer.boardgame.object;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class GameObject extends NewInstanceInGameContext {

    private boolean visible = false;

    protected GameObject(String id) {
        super(id);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
