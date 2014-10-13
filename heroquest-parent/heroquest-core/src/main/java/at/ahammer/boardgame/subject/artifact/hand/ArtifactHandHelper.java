package at.ahammer.boardgame.subject.artifact.hand;

import at.ahammer.boardgame.artifact.Artifact;
import at.ahammer.boardgame.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;

/**
 * Helper-class for {@link Artifact}s used by a {@link at.ahammer.boardgame.subject.GameSubject} in the hand.
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactHandHelper {

    /**
     * Add an {@link at.ahammer.boardgame.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.subject.GameSubject}
     * <p/>
     * Every {@link at.ahammer.boardgame.subject.GameSubject} has a {@link java.util.Set} of {@link ArtifactHandStrategy} where it is defined, which {@link at.ahammer.boardgame.artifact.Artifact} can be used.
     *
     * @param artifact    the {@link at.ahammer.boardgame.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.subject.GameSubject} who gets the{@link at.ahammer.boardgame.artifact.Artifact}
     */
    public void addArtifact(Artifact artifact, GameSubject gameSubject) {
        gameSubject.getHandStrategies().stream().filter(gs -> gs.canHandle(artifact)).findFirst().get().addArtifactToHand(artifact, gameSubject);
    }

    /**
     * Check if the assigned {@link at.ahammer.boardgame.subject.GameSubject} can handle (can use) the assigned {@link at.ahammer.boardgame.artifact.Artifact}.
     *
     * @param artifact    the {@link at.ahammer.boardgame.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.subject.GameSubject} that should use the {@link at.ahammer.boardgame.artifact.Artifact}
     * @return {@code true} it the {@link at.ahammer.boardgame.subject.GameSubject} can handle (can use) the {@link at.ahammer.boardgame.artifact.Artifact}
     */
    public boolean canHandle(Artifact artifact, GameSubject gameSubject) {
        return gameSubject.getHandStrategies().stream().anyMatch(hs -> hs.canHandle(artifact));
//        for (ArtifactHandStrategy artifactHandStrategy : gameSubject.getHandStrategies()) {
//            if (artifactHandStrategy.canHandle(artifact)) {
//                return true;
//            }
//        }
//        return false;
    }
}
