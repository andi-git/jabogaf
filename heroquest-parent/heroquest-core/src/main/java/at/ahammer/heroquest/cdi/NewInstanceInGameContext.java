package at.ahammer.heroquest.cdi;

/**
 * Created by andreas on 02.08.14.
 */
public class NewInstanceInGameContext {

    public NewInstanceInGameContext() {
        GameContext.addNewInstanceInGameContext(this);
    }
}
