package org.jabogaf.core.subject.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.core.artifact.ArtifactNull;

/**
 * Representation of a hand (part of the {@link org.jabogaf.api.subject.GameSubject}).
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
