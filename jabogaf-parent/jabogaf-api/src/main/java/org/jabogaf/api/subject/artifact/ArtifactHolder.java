package org.jabogaf.api.subject.artifact;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingException;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;
import org.jabogaf.api.subject.hand.Hand;

import java.util.List;

/**
 * Created by andreas on 20.01.15.
 */
public interface ArtifactHolder {

    /**
     * Get the {@link Artifact} of the {@link Hand.Type#MAIN}.
     *
     * @return the {@link Artifact} of the {@link Hand.Type#MAIN}
     */
    Artifact getMainHandArtifact();

    /**
     * Get the {@link Artifact} of the {@link Hand.Type#OFF}.
     *
     * @return the {@link Artifact} of the {@link Hand.Type#OFF}
     */
    Artifact getOffHandArtifact();

    /**
     * Get all {@link ArtifactHandlingStrategy} this {@link ArtifactHolder} can handle.
     *
     * @return all {@link ArtifactHandlingStrategy} this {@link ArtifactHolder} can handle
     */
    List<ArtifactHandlingStrategy> getArtifactHandlingStrategies();

    /**
     * Check if the current {@link ArtifactHolder} can handle the assigned {@link ArtifactHandlingStrategy}.
     *
     * @param artifactHandlingStrategy the {@link ArtifactHandlingStrategy} to check
     * @return {@code true}, if the current {@link ArtifactHolder} can handle the assigned {@link ArtifactHandlingStrategy}
     */
    boolean canHandle(ArtifactHandlingStrategy artifactHandlingStrategy);

    /**
     * @see #canHandle(Artifact, Hand.Type)
     */
    boolean canHandle(Artifact artifact);

    /**
     * Check if the current {@link ArtifactHolder} can handle (can use) the assigned {@link Artifact}.
     *
     * @param artifact the {@link Artifact} to add
     * @param handType the {@link Hand.Type} to add the {@link Artifact} to
     * @return {@code true} it the {@link ArtifactHolder} can handle (can use) the {@link Artifact}
     */
    boolean canHandle(Artifact artifact, Hand.Type handType);

    /**
     * @see #addArtifact(Artifact, Hand.Type)
     */
    void addArtifact(Artifact artifact) throws ArtifactHandlingException;

    /**
     * Check if the current {@link ArtifactHolder} can handle (can use) the assigned {@link Artifact}.
     *
     * @param artifact the {@link Artifact} to add
     * @param handType the {@link Hand.Type} to add the {@link Artifact} to
     * @return {@code true} it the {@link ArtifactHolder} can handle (can use) the {@link Artifact}
     * @throws ArtifactHandlingException if the handling is not possible -> check via {@link #canHandle(Artifact, Hand.Type)} first!
     */
    void addArtifact(Artifact artifact, Hand.Type handType) throws ArtifactHandlingException;

    void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy);

    void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy);

    void clearArtifactHandlingStrategies();

    void resetHands();
}
