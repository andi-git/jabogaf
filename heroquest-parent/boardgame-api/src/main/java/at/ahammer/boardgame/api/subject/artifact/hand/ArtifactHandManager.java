package at.ahammer.boardgame.api.subject.artifact.hand;

/**
 * Helper-class for {@link at.ahammer.boardgame.api.artifact.Artifact}s used by a {@link
 * at.ahammer.boardgame.api.subject.GameSubject} in the hand.
 */
public interface ArtifactHandManager {

    /**
     * Add an {@link at.ahammer.boardgame.api.artifact.Artifact} to the hand of a {@link
     * at.ahammer.boardgame.api.subject.GameSubject}
     * <p/>
     * Every {@link at.ahammer.boardgame.api.subject.GameSubject} has a {@link java.util.Set} of {@link
     * at.ahammer.boardgame.api.artifact.weapon.WeaponType} where it is defined, which {@link
     * at.ahammer.boardgame.api.artifact.Artifact} can be used.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @throws at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingException if the handling is not possible -> check via {@link #canHandle(AddArtifactToHandStrategyContext)} first!
     */
    void addArtifact(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) throws ArtifactHandlingException;

    /**
     * Check if the assigned {@link at.ahammer.boardgame.api.subject.GameSubject} can handle (can use) the assigned {@link
     * at.ahammer.boardgame.api.artifact.Artifact}.
     *
     * @param addArtifactToHandStrategyContext the context of the functionality to add an {@link at.ahammer.boardgame.api.artifact.Artifact} to a {@link at.ahammer.boardgame.api.subject.GameSubject}
     * @return {@code true} it the {@link at.ahammer.boardgame.api.subject.GameSubject} can handle (can use) the {@link
     * at.ahammer.boardgame.api.artifact.Artifact}
     */
    boolean canHandle(AddArtifactToHandStrategyContext addArtifactToHandStrategyContext);
}
