package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandManager;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtifactHandManagerBasic implements ArtifactHandManager {

    @Override
    public void addArtifact(Artifact artifact, GameSubject gameSubject) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public boolean canHandle(Artifact artifact, GameSubject gameSubject) {
        throw new RuntimeException("not yet implemented");
    }
}
