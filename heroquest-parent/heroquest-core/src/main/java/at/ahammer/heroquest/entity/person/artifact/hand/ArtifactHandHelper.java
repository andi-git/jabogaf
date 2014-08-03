package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.person.GameSubject;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactHandHelper {

    public void addArtifact(Artifact artifact, GameSubject person) {
        person.getHandStrategies().stream().filter(p -> p.canHandle(artifact)).findFirst().get().addArtifactToHand(artifact, person);
    }

    public boolean canHandle(Artifact artifact, GameSubject person) {
        for (ArtifactHandStrategy artifactHandStrategy : person.getHandStrategies()) {
            if (artifactHandStrategy.canHandle(artifact)) {
                return true;
            }
        }
        return false;
    }
}
