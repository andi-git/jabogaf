package at.ahammer.boardgame.api.subject.hand;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * Representation of a hand (part of the {@link at.ahammer.boardgame.api.subject.GameSubject}).
 */
public interface Hand {

    Artifact getArtifact();

    void setArtifact(Artifact artifact);

    public static enum Type {
        MAIN,
        OFF
    }
}
