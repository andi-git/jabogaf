package org.jabogaf.core.subject.artifact;

import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.subject.artifact.ArtifactHolder;

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
