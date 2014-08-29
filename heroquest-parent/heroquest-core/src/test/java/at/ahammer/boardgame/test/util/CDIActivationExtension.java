package at.ahammer.boardgame.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterTypeDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import java.io.IOException;
import java.io.Serializable;

/**
 * This extension of arquillian will activate all alternatives defined in {@link AlternativesHolder}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class CDIActivationExtension implements Extension, Serializable {

    private static final Logger log = LoggerFactory.getLogger(CDIActivationExtension.class);

    public void addAlternatives(@Observes AfterTypeDiscovery afterTypeDiscovery, BeanManager beanManager) throws IOException, ClassNotFoundException {
        log.info("AfterTypeDiscovery: register alternatives");
        // add all defined alternatives
        afterTypeDiscovery.getAlternatives().addAll(AlternativesHolder.getAlternatives());
        log.info("alternatives: " + afterTypeDiscovery.getAlternatives());
    }
}