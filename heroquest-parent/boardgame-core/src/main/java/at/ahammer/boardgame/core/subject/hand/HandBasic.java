package at.ahammer.boardgame.core.subject.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.core.artifact.ArtifactNull;

/**
 * Representation of a hand (part of the {@link at.ahammer.boardgame.api.subject.GameSubject}).
 */
public class HandBasic implements Hand {

    private final Type type;

    private Artifact artifact;

    public HandBasic(Type type) {
        this(type, new ArtifactNull());
    }

    public HandBasic(Type type, Artifact artifact) {
        this.type = type;
        this.artifact = artifact;
    }

    @Override
    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
}
