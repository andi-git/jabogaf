package at.ahammer.boardgame.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import java.io.Serializable;

/**
 * This extension for arquillian will register the {@link at.ahammer.boardgame.cdi.GameContext}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class GameContextExtention implements Extension, Serializable {

    private static final Logger log = LoggerFactory.getLogger(GameContextExtention.class);

    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
        log.info("BeforeBeanDiscovery. Beginning the scanning process");

    }

    public void registerContexts(@Observes
                                 AfterBeanDiscovery afterBeanDiscovery) {
        log.info("AfterBeanDiscovery: Register context ServiceContext for ServiceScoped beans\n");
        afterBeanDiscovery.addContext(new GameContext());
    }
}