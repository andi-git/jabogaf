package at.ahammer.boardgame.util;

import at.ahammer.boardgame.cdi.GameScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Created by andreas on 07.08.14.
 */
public class LogProducer {

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
