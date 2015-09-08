package org.jabogaf.api.event;

/**
 * All events fired in {@link #withinOwnEventGroup(Runnable)} are grouped together in an own {@link
 * GameStateChangedEventGroup}.
 * <p>
 * <u>Note</u>: this component needs an active {@code GameContext}!
 */
public interface GameStateChangedEventGroupHandler {

    /**
     * Run the code within an own {@link GameStateChangedEventGroup}. All fired {@link GameStateChangedEvent}s are
     * grouped in an own {@link GameStateChangedEventGroup}.
     *
     * @param runnable - the code where all fired {@link GameStateChangedEvent}s are grouped in an own {@link
     *                 GameStateChangedEventGroup}.
     * @return the {@link GameStateChangedEventGroup} where the {@link GameStateChangedEvent}s are grouped together
     */
    GameStateChangedEventGroup withinOwnEventGroup(Runnable runnable);

    /**
     * Get the current/active {@link GameStateChangedEventGroup}.
     *
     * @return the current/active {@link GameStateChangedEventGroup}
     */
    GameStateChangedEventGroup getCurrentGameStateChangedEventGroup();

}
