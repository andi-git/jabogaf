package at.ahammer.boardgame.api.subject.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;

public class NullArtifact extends Artifact {

    public NullArtifact() {
        super(String.valueOf(System.nanoTime()));
    }
}
