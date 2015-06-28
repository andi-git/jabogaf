package org.jabogaf.api.subject;

import org.jabogaf.api.artifact.Artifact;

/**
 * The function to set the {@link Artifact} of the main- and off-hand.
 */
@FunctionalInterface
public interface SetterOfArtifactsForHands {

    /**
     * Set the {@link Artifact} for the main- and the off-hand.
     *
     * @param artifactForMainHand  {@link Artifact} for main-hand
     * @param artifactForOffHand {@link Artifact} for off-hand
     */
    void setHands(Artifact artifactForMainHand, Artifact artifactForOffHand);
}
