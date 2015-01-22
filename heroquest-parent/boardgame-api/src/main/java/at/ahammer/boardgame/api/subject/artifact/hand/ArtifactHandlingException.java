package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.artifact.ArtifactHolder;
import at.ahammer.boardgame.api.subject.hand.Hand;

/**
 * This exception will be thrown, if a {@link at.ahammer.boardgame.api.artifact.Artifact} can not be handled by a {@link
 * at.ahammer.boardgame.api.subject.GameSubject}.
 */
public class ArtifactHandlingException extends Exception {

    private final Artifact artifact;

    private final Hand.Type handType;

    private final ArtifactHolder artifactHolder;

    public ArtifactHandlingException(String message, Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder) {
        super(message);
        this.artifact = artifact;
        this.handType = handType;
        this.artifactHolder = artifactHolder;
    }

    public ArtifactHandlingException(String message, Exception e, Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder) {
        super(message, e);
        this.artifact = artifact;
        this.handType = handType;
        this.artifactHolder = artifactHolder;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public Hand.Type getHandType() {
        return handType;
    }

    public ArtifactHolder getArtifactHolder() {
        return artifactHolder;
    }
}
