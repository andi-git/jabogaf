package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.person.GameSubject;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class ArtifactHandStrategy {

    protected abstract boolean canHandle(Artifact artifact);

    protected abstract void addArtifactToHand(Artifact artifact, GameSubject person);
}
