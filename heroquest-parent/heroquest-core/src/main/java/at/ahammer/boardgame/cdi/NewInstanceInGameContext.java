package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Created by andreas on 02.08.14.
 */
public abstract class NewInstanceInGameContext {

    private final BeanManager beanManager;

    public NewInstanceInGameContext(BeanManager beanManager) {
        this.beanManager = beanManager;
        GameContext.addNewInstanceInGameContext(beanManager, this);
    }

    protected <T> T fromGameContext(Class<T> clazz) {
        return BeanProvider.getBean(beanManager, clazz);
    }
}
