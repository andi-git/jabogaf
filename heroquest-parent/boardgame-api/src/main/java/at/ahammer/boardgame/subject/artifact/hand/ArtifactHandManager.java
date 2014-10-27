package at.ahammer.boardgame.subject.artifact.hand;

import at.ahammer.boardgame.artifact.Artifact;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * Helper-class for {@link at.ahammer.boardgame.artifact.Artifact}s used by a {@link
 * at.ahammer.boardgame.subject.GameSubject} in the hand.
 */
public interface ArtifactHandManager {

    /**
     * Add an {@link at.ahammer.boardgame.artifact.Artifact} to the hand of a {@link
     * at.ahammer.boardgame.subject.GameSubject}
     * <p/>
     * Every {@link at.ahammer.boardgame.subject.GameSubject} has a {@link java.util.Set} of {@link
     * at.ahammer.boardgame.artifact.weapon.WeaponType} where it is defined, which {@link
     * at.ahammer.boardgame.artifact.Artifact} can be used.
     *
     * @param artifact    the {@link at.ahammer.boardgame.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.subject.GameSubject} who gets the{@link
     *                    at.ahammer.boardgame.artifact.Artifact}
     */
    void addArtifact(Artifact artifact, GameSubject gameSubject);

    /**
     * Check if the assigned {@link at.ahammer.boardgame.subject.GameSubject} can handle (can use) the assigned {@link
     * at.ahammer.boardgame.artifact.Artifact}.
     *
     * @param artifact    the {@link at.ahammer.boardgame.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.subject.GameSubject} that should use the {@link
     *                    at.ahammer.boardgame.artifact.Artifact}
     * @return {@code true} it the {@link at.ahammer.boardgame.subject.GameSubject} can handle (can use) the {@link
     * at.ahammer.boardgame.artifact.Artifact}
     */
    boolean canHandle(Artifact artifact, GameSubject gameSubject);
}
