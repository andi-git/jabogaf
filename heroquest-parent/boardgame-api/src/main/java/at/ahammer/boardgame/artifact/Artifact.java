package at.ahammer.boardgame.artifact;

import at.ahammer.boardgame.cdi.GameContextBean;

/**
 * An {@link at.ahammer.boardgame.artifact.Artifact} cast an object in the board game which can be used by a {@link
 * at.ahammer.boardgame.subject.GameSubject}.
 */
public abstract class Artifact extends GameContextBean {

    private final HandCount handCount;

    /**
     * Create a new {@link at.ahammer.boardgame.artifact.Artifact}
     *
     * @param id the id
     */
    protected Artifact(String id) {
        this(id, HandCount.NONE);
    }

    /**
     * Create a new {@link at.ahammer.boardgame.artifact.Artifact}
     *
     * @param id        the id
     * @param handCount the {@link at.ahammer.boardgame.artifact.HandCount}
     */
    protected Artifact(String id, HandCount handCount) {
        super(id);
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
