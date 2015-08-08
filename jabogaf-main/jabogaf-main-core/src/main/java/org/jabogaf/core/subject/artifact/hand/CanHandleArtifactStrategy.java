package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.hand.Hand;

/**
 * The function that determines if the {@link org.jabogaf.api.artifact.Artifact} can be handled.
 */
@FunctionalInterface
public interface CanHandleArtifactStrategy {

    boolean check(Artifact artifact, Hand.Type handType);
}
