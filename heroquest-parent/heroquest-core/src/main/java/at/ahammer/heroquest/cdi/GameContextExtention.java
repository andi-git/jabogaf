package at.ahammer.heroquest.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameContextExtention implements Extension {

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
