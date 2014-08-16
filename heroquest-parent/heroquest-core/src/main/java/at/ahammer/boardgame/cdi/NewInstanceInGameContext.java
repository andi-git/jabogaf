package at.ahammer.boardgame.cdi;

/**
 * Created by andreas on 02.08.14.
 */
public abstract class NewInstanceInGameContext {

    public NewInstanceInGameContext() {
        GameContext.addNewInstanceInGameContext(this);
    }

    protected <T> T fromGameContext(Class<T> clazz) {
        return GameContext.current().getFromDynamicContext(clazz);
    }
}
