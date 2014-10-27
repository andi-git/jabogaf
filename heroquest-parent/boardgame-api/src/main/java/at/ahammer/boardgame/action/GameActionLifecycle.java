package at.ahammer.boardgame.action;

import at.ahammer.boardgame.action.event.AfterActionEvent;
import at.ahammer.boardgame.action.event.BeforeActionEvent;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * This is the lifecycle of a {@link at.ahammer.boardgame.action.GameAction}. For details see {@link #perform(GameActionPreferences)}
 * <p/>
 * This component doesn't have a state and can be {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface GameActionLifecycle {

    /**
     * Performs the current action with the following lifecycle:
     *
     * <ul>
     *     <li>fire the {@link at.ahammer.boardgame.action.event.BeforeActionEvent}</li>
     *     <li>check the prerequisites</li>
     *     <li>perform the action</li>
     *     <li>fire the {@link at.ahammer.boardgame.action.event.AfterActionEvent}</li>
     * </ul>
     *
     * All the functions / data to perform the action are stored in the assigned {@link at.ahammer.boardgame.action.GameActionPreferences}
     *
     * @param gameActionPreferences all preferences defined for the lifecycle-execution
     * @throws ActionNotPossibleException
     */
    void perform(GameActionPreferences gameActionPreferences) throws ActionNotPossibleException;

}
