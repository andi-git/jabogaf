package org.jabogaf.api.action;

import org.jabogaf.api.event.AfterActionEvent;
import org.jabogaf.api.event.BeforeActionEvent;

/**
 * This is the lifecycle of a {@link GameAction}. For details see {@link #perform(GameActionPreferences)}
 * <p/>
 * This component doesn't have a state and can be {@link javax.enterprise.context.ApplicationScoped}.
 */
public interface GameActionLifecycle {

    /**
     * Performs the current action with the following lifecycle:
     *
     * <ul>
     *     <li>fire the {@link BeforeActionEvent}</li>
     *     <li>check the prerequisites</li>
     *     <li>perform the action</li>
     *     <li>fire the {@link AfterActionEvent}</li>
     * </ul>
     *
     * All the functions / data to perform the action are stored in the assigned {@link GameActionPreferences}
     *
     * @param gameActionPreferences all preferences defined for the lifecycle-execution
     * @throws ActionNotPossibleException
     */
    void perform(GameActionPreferences gameActionPreferences) throws ActionNotPossibleException;

}
