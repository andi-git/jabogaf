package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.SetterOfArtifactsForHands;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.hand.Hand;

import javax.inject.Inject;

/**
 * The strategy that allows a {@link at.ahammer.boardgame.api.subject.GameSubject} to handle a concrete {@link
 * at.ahammer.boardgame.api.artifact.Artifact}.
 */
public interface ArtifactHandlingStrategy {

    /**
     * Check if the concrete {@link at.ahammer.boardgame.api.artifact.Artifact} can be handled by the current {@link
     * at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to check
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link
     *                 at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} if the the concrete {@link at.ahammer.boardgame.api.artifact.Artifact} can be handled by the
     * current {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}
     */
    boolean canHandle(Artifact artifact, Hand.Type handType);

    /**
     * This method encapsulates the strategy to add a concrete {@link at.ahammer.boardgame.api.artifact.Artifact} to a
     * {@link at.ahammer.boardgame.api.subject.GameSubject}.
     *
     * @param artifact                  the {@link at.ahammer.boardgame.api.artifact.Artifact} to set
     * @param handType                  the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to set the {@link
     *                                  at.ahammer.boardgame.api.artifact.Artifact} to
     * @param artifactHolder            the {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} which holds
     *                                  the {@link at.ahammer.boardgame.api.artifact.Artifact}
     * @param setterOfArtifactsForHands the strategy to set the {@link at.ahammer.boardgame.api.artifact.Artifact} in
     *                                  the {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException if the handling is not possible
     *                                                                                  -> check via {@link #canHandle(at.ahammer.boardgame.api.artifact.Artifact,
     *                                                                                  at.ahammer.boardgame.api.subject.hand.Hand.Type)}
     *                                                                                  first!
     */
    void addArtifactToHand(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) throws ArtifactHandlingException;
}
