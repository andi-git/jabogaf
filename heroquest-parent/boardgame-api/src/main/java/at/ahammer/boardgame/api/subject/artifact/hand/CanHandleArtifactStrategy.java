package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.subject.hand.Hand;

/**
 * The function that determines if the {@link at.ahammer.boardgame.api.artifact.Artifact} can be handled.
 */
@FunctionalInterface
public interface CanHandleArtifactStrategy {

    boolean check(Artifact artifact, Hand.Type handType);
}
