package at.ahammer.boardgame.core.resource;

import at.ahammer.boardgame.api.resource.Resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ResourcesNewInstanceProducer {

//    @Produces
    public Resources newInstance() {
        return new ResourcesBasic();
    }
}
