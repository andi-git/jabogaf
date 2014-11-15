package at.ahammer.boardgame.api.artifact;

public class ArtifactException extends Exception {

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
