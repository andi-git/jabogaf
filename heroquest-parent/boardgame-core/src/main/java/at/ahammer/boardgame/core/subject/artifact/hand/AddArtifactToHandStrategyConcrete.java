package at.ahammer.boardgame.core.subject.artifact.hand;

/**
 * The concrete function of how to add the {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.api.subject.GameSubject}.
 */
@FunctionalInterface
public interface AddArtifactToHandStrategyConcrete {

    /**
     * The concrete function of how to add the {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.api.subject.GameSubject}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}
     */
    void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext);
}
