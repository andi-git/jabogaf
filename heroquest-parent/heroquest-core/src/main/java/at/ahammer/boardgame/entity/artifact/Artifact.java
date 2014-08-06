package at.ahammer.boardgame.entity.artifact;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

import javax.enterprise.inject.spi.BeanManager;

/**
 * An {@link at.ahammer.boardgame.entity.artifact.Artifact} cast an object in the board game which can be used by a {@link at.ahammer.boardgame.entity.subject.GameSubject}.
 * <p/>
 * Created by andreas on 26.07.14.
 */
public abstract class Artifact extends NewInstanceInGameContext {

    private final String name;

    private final HandCount handCount;

    protected Artifact(BeanManager beanManager, String name) {
        this(beanManager, name, HandCount.NONE);
    }

    protected Artifact(BeanManager beanManager, String name, HandCount handCount) {
        super(beanManager);
        this.name = name;
        this.handCount = handCount;
    }

    public String getName() {
        return name;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
