package org.jabogaf.api.subject.artifact.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.artifact.ArtifactHolder;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.api.subject.GameSubject;

/**
 * This exception will be thrown, if a {@link Artifact} can not be handled by a {@link GameSubject}.
 */
public class ArtifactHandlingException extends RuntimeException {

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
