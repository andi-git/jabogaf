package at.ahammer.boardgame.subject.artifact.hand;

import at.ahammer.boardgame.artifact.Artifact;
import at.ahammer.boardgame.subject.GameSubject;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class ArtifactHandStrategy {

    protected abstract boolean canHandle(Artifact artifact);

    protected abstract void addArtifactToHand(Artifact artifact, GameSubject person);
}
