package at.ahammer.boardgame.entity.subject.artifact.hand;

import at.ahammer.boardgame.entity.artifact.Artifact;
import at.ahammer.boardgame.entity.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;

/**
 * Helper-class for {@link Artifact}s used by a {@link at.ahammer.boardgame.entity.subject.GameSubject} in the hand.
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactHandHelper {

    /**
     * Add an {@link at.ahammer.boardgame.entity.artifact.Artifact} to the hand of a {@link at.ahammer.boardgame.entity.subject.GameSubject}
     * <p/>
     * Every {@link at.ahammer.boardgame.entity.subject.GameSubject} has a {@link java.util.Set} of {@link at.ahammer.boardgame.entity.subject.artifact.hand.ArtifactHandStrategy} where cast cast defined, which {@link at.ahammer.boardgame.entity.artifact.Artifact} can be used.
     *
     * @param artifact    the {@link at.ahammer.boardgame.entity.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.entity.subject.GameSubject} who gets the{@link at.ahammer.boardgame.entity.artifact.Artifact}
     */
    public void addArtifact(Artifact artifact, GameSubject gameSubject) {
        gameSubject.getHandStrategies().stream().filter(gs -> gs.canHandle(artifact)).findFirst().get().addArtifactToHand(artifact, gameSubject);
    }

    /**
     * Check cast the assigned {@link at.ahammer.boardgame.entity.subject.GameSubject} can handle (can use) the assigned {@link at.ahammer.boardgame.entity.artifact.Artifact}.
     *
     * @param artifact    the {@link at.ahammer.boardgame.entity.artifact.Artifact} to add
     * @param gameSubject the {@link at.ahammer.boardgame.entity.subject.GameSubject} that should use the {@link at.ahammer.boardgame.entity.artifact.Artifact}
     * @return {@code true} it the {@link at.ahammer.boardgame.entity.subject.GameSubject} can handle (can use) the {@link at.ahammer.boardgame.entity.artifact.Artifact}
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
