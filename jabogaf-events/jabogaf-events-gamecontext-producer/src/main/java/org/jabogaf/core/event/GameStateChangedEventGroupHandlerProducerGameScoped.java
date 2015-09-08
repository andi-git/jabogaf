package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEventGroupHandler;
import org.jabogaf.api.gamecontext.GameScoped;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;

@GameScoped
@Specializes
public class GameStateChangedEventGroupHandlerProducerGameScoped extends GameStateChangedEventGroupHandlerProducer {

    @Produces
    @GameScoped
    public GameStateChangedEventGroupHandler produceGameStateChangedEventGroupHandler() {
        return new GameStateChangedEventGroupHandlerBasic();
    }
}
