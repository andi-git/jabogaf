package at.ahammer.boardgame.api.subject.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;

import java.util.List;

/**
 * Created by andreas on 20.01.15.
 */
public interface ArtifactHolder {

    /**
     * Get the {@link at.ahammer.boardgame.api.artifact.Artifact} of the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#MAIN}.
     *
     * @return the {@link at.ahammer.boardgame.api.artifact.Artifact} of the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#MAIN}
     */
    Artifact getMainHandArtifact();

    /**
     * Get the {@link at.ahammer.boardgame.api.artifact.Artifact} of the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#OFF}.
     *
     * @return the {@link at.ahammer.boardgame.api.artifact.Artifact} of the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#OFF}
     */
    Artifact getOffHandArtifact();

    /**
     * Get all {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy} this {@link
     * at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle.
     *
     * @return all {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy} this {@link
     * at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle
     */
    List<ArtifactHandlingStrategy> getArtifactHandlingStrategies();

    /**
     * Check if the current {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle the assigned
     * {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}.
     *
     * @param artifactHandlingStrategy the {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}
     *                                 to check
     * @return {@code true}, if the current {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle
     * the assigned {@link at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy}
     */
    boolean canHandle(ArtifactHandlingStrategy artifactHandlingStrategy);

    /**
     * @see #canHandle(at.ahammer.boardgame.api.artifact.Artifact, at.ahammer.boardgame.api.subject.hand.Hand.Type)
     */
    boolean canHandle(Artifact artifact);

    /**
     * Check if the current {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle (can use) the
     * assigned {@link at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link
     *                 at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} it the {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle (can use)
     * the {@link at.ahammer.boardgame.api.artifact.Artifact}
     */
    boolean canHandle(Artifact artifact, Hand.Type handType);

    /**
     * @see #addArtifact(at.ahammer.boardgame.api.artifact.Artifact, at.ahammer.boardgame.api.subject.hand.Hand.Type)
     */
    void addArtifact(Artifact artifact) throws ArtifactHandlingException;

    /**
     * Check if the current {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle (can use) the
     * assigned {@link at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param artifact the {@link at.ahammer.boardgame.api.artifact.Artifact} to add
     * @param handType the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type} to add the {@link
     *                 at.ahammer.boardgame.api.artifact.Artifact} to
     * @return {@code true} it the {@link at.ahammer.boardgame.api.subject.artifact.ArtifactHolder} can handle (can use)
     * the {@link at.ahammer.boardgame.api.artifact.Artifact}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException if the handling is not possible
     *                                                                                  -> check via {@link #canHandle(at.ahammer.boardgame.api.artifact.Artifact,
     *                                                                                  at.ahammer.boardgame.api.subject.hand.Hand.Type)}
     *                                                                                  first!
     */
    void addArtifact(Artifact artifact, Hand.Type handType) throws ArtifactHandlingException;

    void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy);

    void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy);

    void clearArtifactHandlingStrategies();

    void resetHands();
}
