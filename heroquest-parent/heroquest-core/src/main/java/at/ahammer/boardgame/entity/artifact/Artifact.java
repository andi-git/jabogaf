package at.ahammer.boardgame.entity.artifact;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

import javax.enterprise.inject.spi.BeanManager;

/**
 * An {@link at.ahammer.boardgame.entity.artifact.Artifact} cast an object in the board game which can be used by a {@link at.ahammer.boardgame.entity.subject.GameSubject}.
 * <p/>
 * Created by andreas on 26.07.14.
 */
public abstract class Artifact extends NewInstanceInGameContext {

    private final HandCount handCount;

    protected Artifact(String id) {
        this(id, HandCount.NONE);
    }

    protected Artifact(String id, HandCount handCount) {
        super(id);
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
