package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Created by ahammer on 04.08.2014.
 */
public class BeanManagerProducer {

    @Inject
    private BeanManager beanManager;

    public BeanManager getBeanManager() {
        return beanManager;
    }
}
