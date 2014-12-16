package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * The function to set the {@link at.ahammer.boardgame.api.artifact.Artifact} of the main- and off-hand.
 */
@FunctionalInterface
public interface SetterOfArtifactsForHands {

    /**
     * Set the {@link at.ahammer.boardgame.api.artifact.Artifact} for the main- and the off-hand.
     *
     * @param artifactForMainHand  {@link at.ahammer.boardgame.api.artifact.Artifact} for main-hand
     * @param artifactForOffHand {@link at.ahammer.boardgame.api.artifact.Artifact} for off-hand
     */
    void setHands(Artifact artifactForMainHand, Artifact artifactForOffHand);
}
