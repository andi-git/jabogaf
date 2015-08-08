package org.jabogaf.api.subject.artifact.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfArtifactsForHands;
import org.jabogaf.api.subject.artifact.ArtifactHolder;
import org.jabogaf.api.subject.hand.Hand;

/**
 * The strategy that allows a {@link GameSubject} to handle a concrete {@link Artifact}.
 */
public interface ArtifactHandlingStrategy {

    /**
     * Check if the concrete {@link Artifact} can be handled by the current {@link ArtifactHandlingStrategy}.
     *
     * @param artifact the {@link Artifact} to check
     * @param handType the {@link Hand.Type} to add the {@link Artifact} to
     * @return {@code true} if the the concrete {@link Artifact} can be handled by the
     * current {@link ArtifactHandlingStrategy}
     */
    boolean canHandle(Artifact artifact, Hand.Type handType);

    /**
     * This method encapsulates the strategy to add a concrete {@link Artifact} to a {@link GameSubject}.
     *
     * @param artifact                  the {@link Artifact} to set
     * @param handType                  the {@link Hand.Type} to set the {@link Artifact} to
     * @param artifactHolder            the {@link ArtifactHolder} which holds
     *                                  the {@link Artifact}
     * @param setterOfArtifactsForHands the strategy to set the {@link Artifact} in the {@link ArtifactHolder}
     * @throws ArtifactHandlingException if the handling is not possible -> check via {@link #canHandle(Artifact, Hand.Type)} first!
     */
    void addArtifactToHand(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) throws ArtifactHandlingException;
}
