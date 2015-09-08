package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The {@link List} of the {@link GameStateChangedEvent} has to be unique in a certain context. <br/> <br/>
 * <i><u>Note</u></i>: To use it in game-context, add the module jabogaf-event-gamecontext-producer as dependency.
 */
@ApplicationScoped
public class GameStateChangedCollectionProducerBasic {

    @Produces
    @RequestScoped
    protected Map<UUID, GameStateChangedEventGroup> produceGameStateChangedList() {
        return new HashMap<>();
    }
}
