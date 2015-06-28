package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.util.cdi.NullImplementation;

import javax.enterprise.context.ApplicationScoped;

/**
 * Null-implementation of {@link org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy}.
 */
@ApplicationScoped
@NullImplementation
public class ArtifactHandlingStrategyNull extends ArtifactHandlingStrategyBasic {

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            // nothing to do here
        };
    }

    @Override
    protected CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            return false;
        };
    }
}
