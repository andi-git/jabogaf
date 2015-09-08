package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEvent;
import org.jabogaf.api.event.GameStateChangedEventGroup;
import org.jabogaf.api.gamecontext.GameScoped;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import java.util.*;

/**
 * The {@link List} of the {@link GameStateChangedEvent} has to be unique in a certain context. <br/> <br/>
 * <i><u>Note</u></i>: This implementation is bound to {@link GameScoped}.
 */
@ApplicationScoped
@Specializes
public class GameStateChangedCollectionProducerBasicGameScoped extends GameStateChangedCollectionProducerBasic {

    @Produces
    @GameScoped
    @Override
    protected Map<UUID, GameStateChangedEventGroup> produceGameStateChangedList() {
        return new HashMap<>();
    }
}
