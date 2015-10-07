package org.jabogaf.core.artifact;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.artifact.HandCount;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

/**
 * An {@link org.jabogaf.core.artifact.ArtifactBasic} cast an object in the board game which can be used by a {@link
 * org.jabogaf.api.subject.GameSubject}.
 */
public abstract class ArtifactBasic extends GameContextBeanBasic implements Artifact {

    private final HandCount handCount;

    /**
     * Create a new {@link org.jabogaf.core.artifact.ArtifactBasic}
     *
     * @param id the id
     */
    protected ArtifactBasic(String id) {
        this(id, HandCount.NONE);
    }

    /**
     * Create a new {@link org.jabogaf.core.artifact.ArtifactBasic}
     *
     * @param id        the id
     * @param handCount the {@link org.jabogaf.api.artifact.HandCount}
     */
    protected ArtifactBasic(String id, HandCount handCount) {
        super(id);
        this.handCount = handCount;
    }

    @Override
    public HandCount getHandCount() {
        return handCount;
    }
}
