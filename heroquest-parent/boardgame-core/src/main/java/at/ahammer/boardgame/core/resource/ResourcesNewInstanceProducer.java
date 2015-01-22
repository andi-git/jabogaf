package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.resource.Resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ResourcesNewInstanceProducer {

    @Inject
    private GameContextManager gameContextManager;

    @Produces
    public Resources newInstance() {
        return gameContextManager.resolve(new ResourcesBasic());
    }
}
