package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChanged;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Specializes
public class GameStateChangedListProducer extends GameStateChangedListProducerBasic {

    @Produces
    @RequestScoped
    @Override
    protected List<GameStateChanged> produceGameStateChangedList() {
        return new ArrayList<>();
    }
}
