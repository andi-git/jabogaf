package org.jabogaf.core.subject.artifact.hand;

/**
 * The concrete function of how to add the {@link org.jabogaf.api.artifact.Artifact} to the hand of a {@link org.jabogaf.api.subject.GameSubject}.
 */
@FunctionalInterface
public interface AddArtifactToHandStrategyConcrete {

    /**
     * The concrete function of how to add the {@link org.jabogaf.api.artifact.Artifact} to the hand of a {@link org.jabogaf.api.subject.GameSubject}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link org.jabogaf.api.artifact.Artifact} to a {@link org.jabogaf.api.subject.GameSubject}
     */
    void addArtifactToHand(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext);
}
