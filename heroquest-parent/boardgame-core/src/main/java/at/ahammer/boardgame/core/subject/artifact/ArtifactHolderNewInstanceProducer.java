package at.ahammer.boardgame.core.subject.artifact;

import at.ahammer.boardgame.api.cdi.GameContextManager;
import at.ahammer.boardgame.api.resource.Resources;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.core.resource.ResourcesBasic;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ArtifactHolderNewInstanceProducer {

    @Inject
    private GameContextManager gameContextManager;

    @Produces
    public ArtifactHolder newInstance() {
        return gameContextManager.resolve(new ArtifactHolderBasic());
    }
}
