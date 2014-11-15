package at.ahammer.boardgame.api.artifact;

import at.ahammer.boardgame.api.cdi.GameContextBean;

/**
 * An {@link Artifact} cast an object in the board game which can be used by a {@link
 * at.ahammer.boardgame.api.subject.GameSubject}.
 */
public abstract class Artifact extends GameContextBean {

    private final HandCount handCount;

    /**
     * Create a new {@link Artifact}
     *
     * @param id the id
     */
    protected Artifact(String id) {
        this(id, HandCount.NONE);
    }

    /**
     * Create a new {@link Artifact}
     *
     * @param id        the id
     * @param handCount the {@link HandCount}
     */
    protected Artifact(String id, HandCount handCount) {
        super(id);
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
