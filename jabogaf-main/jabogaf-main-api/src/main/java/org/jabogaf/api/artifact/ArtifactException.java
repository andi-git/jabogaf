package org.jabogaf.api.artifact;

public class ArtifactException extends RuntimeException {

    public ArtifactException() {
        super();
    }

    public ArtifactException(String message) {
        super(message);
    }

    public ArtifactException(String message, Throwable cause) {
        super(message, cause);
    }
}
