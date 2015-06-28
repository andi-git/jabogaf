package org.jabogaf.api.subject.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.GameSubject;

/**
 * Representation of a hand (part of the {@link GameSubject}).
 */
public interface Hand {

    Artifact getArtifact();

    void setArtifact(Artifact artifact);

    public static enum Type {
        MAIN,
        OFF
    }
}
