package org.jabogaf.core.resource;

import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.resource.Resources;

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
