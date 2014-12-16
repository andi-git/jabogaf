package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.hand.Hand;

/**
 * This exception will be thrown, if a {@link at.ahammer.boardgame.api.artifact.Artifact} can not be handled by a {@link at.ahammer.boardgame.api.subject.GameSubject}.
 */
public class ArtifactHandlingException extends Exception {

    private final Artifact artifact;

    private final Hand.Type handType;

    private final GameSubject gameSubject;

    public ArtifactHandlingException(String message, Artifact artifact, Hand.Type handType, GameSubject gameSubject) {
        super(message);
        this.artifact = artifact;
        this.handType = handType;
        this.gameSubject = gameSubject;
    }

    public ArtifactHandlingException(String message, AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) {
        super(message);
        this.artifact = addArtifactToHandStrategyContext.getArtifact();
        this.handType = addArtifactToHandStrategyContext.getHandType();
        this.gameSubject = addArtifactToHandStrategyContext.getGameSubject();
    }

    public ArtifactHandlingException(String message, Exception e, AddArtifactToHandStrategyContext addArtifactToHandStrategyContext) {
        super(message, e);
        this.artifact = addArtifactToHandStrategyContext.getArtifact();
        this.handType = addArtifactToHandStrategyContext.getHandType();
        this.gameSubject = addArtifactToHandStrategyContext.getGameSubject();
    }
}
