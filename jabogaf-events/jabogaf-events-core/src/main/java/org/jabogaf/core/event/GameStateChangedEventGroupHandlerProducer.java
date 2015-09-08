package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEventGroupHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GameStateChangedEventGroupHandlerProducer {

    @Produces
    @RequestScoped
    public GameStateChangedEventGroupHandler produceGameStateChangedEventGroupHandler() {
        return new GameStateChangedEventGroupHandlerBasic();
    }
}
