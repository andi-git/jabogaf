package at.ahammer.boardgame.artifact;

/**
 * Created by andreas on 26.07.14.
 */
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
