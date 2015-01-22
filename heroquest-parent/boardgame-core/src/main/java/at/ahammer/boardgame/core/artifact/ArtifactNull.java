package at.ahammer.boardgame.core.artifact;

public class ArtifactNull extends ArtifactBasic {

    public ArtifactNull() {
        super(String.valueOf(System.nanoTime()));
    }
}
