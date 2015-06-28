package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;

/**
 * The general function of how to add the {@link org.jabogaf.api.artifact.Artifact} to the hand of a {@link org.jabogaf.api.subject.GameSubject}.
 */
@FunctionalInterface
public interface AddArtifactToHandStrategyGeneral {

    /**
     * The general function of how to add the {@link org.jabogaf.api.artifact.Artifact} to the hand of a {@link org.jabogaf.api.subject.GameSubject}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link org.jabogaf.api.artifact.Artifact} to a {@link org.jabogaf.api.subject.GameSubject}
     * @throws org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException
     */
    void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException;
}
