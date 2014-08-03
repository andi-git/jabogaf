package at.ahammer.heroquest.cdi;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 02.08.14.
 */
@GameScoped
public class NewInstanceInGameContextCache {

    private Set<NewInstanceInGameContext> newInstancesInGameContext = new HashSet<>();

    public void addNewInstanceToGameContext(NewInstanceInGameContext newInstanceInGameContext) {
        newInstancesInGameContext.add(newInstanceInGameContext);
    }

    public Set<NewInstanceInGameContext> getNewInstancesInGameContext() {
        return newInstancesInGameContext;
    }
}
