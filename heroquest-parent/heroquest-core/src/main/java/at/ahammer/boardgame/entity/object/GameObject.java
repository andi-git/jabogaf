package at.ahammer.boardgame.entity.object;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class GameObject extends NewInstanceInGameContext {

    public abstract boolean isVisible();
}
