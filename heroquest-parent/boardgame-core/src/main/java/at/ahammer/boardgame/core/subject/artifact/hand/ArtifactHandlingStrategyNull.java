package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.util.cdi.NullImplementation;

import javax.enterprise.context.ApplicationScoped;

/**
 * Null-implementation of {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}.
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
