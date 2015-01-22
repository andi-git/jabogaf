package at.ahammer.boardgame.core.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.HandCount;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;

/**
 * An {@link at.ahammer.boardgame.core.artifact.ArtifactBasic} cast an object in the board game which can be used by a {@link
 * at.ahammer.boardgame.api.subject.GameSubject}.
 */
public abstract class ArtifactBasic extends GameContextBeanBasic implements Artifact {

    private final HandCount handCount;

    /**
     * Create a new {@link at.ahammer.boardgame.core.artifact.ArtifactBasic}
     *
     * @param id the id
     */
    protected ArtifactBasic(String id) {
        this(id, HandCount.NONE);
    }

    /**
     * Create a new {@link at.ahammer.boardgame.core.artifact.ArtifactBasic}
     *
     * @param id        the id
     * @param handCount the {@link at.ahammer.boardgame.api.artifact.HandCount}
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
