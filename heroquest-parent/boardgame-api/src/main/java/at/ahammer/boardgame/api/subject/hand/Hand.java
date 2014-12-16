package at.ahammer.boardgame.api.subject.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.artifact.NullArtifact;

/**
 * Representation of a hand (part of the {@link at.ahammer.boardgame.api.subject.GameSubject}).
 */
public class Hand {

    private final Type type;

    private Artifact artifact;

    public Hand(Type type) {
        this(type, new NullArtifact());
    }

    public Hand(Type type, Artifact artifact) {
        this.type = type;
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public static enum Type {
        MAIN,
        OFF
    }
}
