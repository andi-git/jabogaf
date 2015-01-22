package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;

/**
 * The general function of how to add the {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.api.subject.GameSubject}.
 */
@FunctionalInterface
public interface AddArtifactToHandStrategyGeneral {

    /**
     * The general function of how to add the {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.api.subject.GameSubject}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException
     */
    void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException;
}
