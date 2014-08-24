package at.ahammer.boardgame.object;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class GameObject extends NewInstanceInGameContext {

    protected GameObject(String id) {
        super(id);
    }

    public abstract boolean isVisible();
}
