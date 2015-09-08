package org.jabogaf.core.event;

import org.jabogaf.api.event.GameStateChangedEventGroup;
import org.jabogaf.api.event.GameStateChangedEventGroupHandler;

import javax.enterprise.inject.Typed;

@Typed // use a producer for certain context
public class GameStateChangedEventGroupHandlerBasic implements GameStateChangedEventGroupHandler {

    private final GameStateChangedEventGroup gameStateChangedEventGroupDefault = new GameStateChangedEventGroupBasic();

    private GameStateChangedEventGroup gameStateChangedEventGroupCurrent = gameStateChangedEventGroupDefault;

    @Override
    public GameStateChangedEventGroup withinOwnEventGroup(Runnable runnable) {
        GameStateChangedEventGroup returnGameStateChangedEventGroup = new GameStateChangedEventGroupBasic();
        gameStateChangedEventGroupCurrent = returnGameStateChangedEventGroup;
        runnable.run();
        gameStateChangedEventGroupCurrent = gameStateChangedEventGroupDefault;
        return returnGameStateChangedEventGroup;
    }

    @Override
    public GameStateChangedEventGroup getCurrentGameStateChangedEventGroup() {
        return gameStateChangedEventGroupCurrent;
    }
}
