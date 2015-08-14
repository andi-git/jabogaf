package org.jabogaf.core.event;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.api.event.GameStateChanged;

import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link List} of the {@link GameStateChanged} has to be unique in a certain context. In the default implementation
 * (this class) it is bound to {@link GameScoped}.
 */
@GameScoped
public class GameStateChangedListProducerBasic {

    @Produces
    @GameScoped
    protected List<GameStateChanged> produceGameStateChangedList() {
        return new ArrayList<>();
    }
}
