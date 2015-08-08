package org.jabogaf.api.action;

import org.jabogaf.api.event.ActionEventCreation;
import org.jabogaf.api.event.AfterActionEvent;
import org.jabogaf.api.event.BeforeActionEvent;

/**
 * All data or functions to perform a {@link GameAction}.
 */
public interface GameActionPreferences {

    GameActionPreferences addPrerequisite(ActionPrerequisite actionPrerequisite);

    GameActionPreferences addPerform(ActionPerform actionPerform);

    GameActionPreferences addBeforeActionEventCreation(ActionEventCreation<BeforeActionEvent> beforeActionEventCreation);

    GameActionPreferences addAfterActionEventCreation(ActionEventCreation<AfterActionEvent> afterActionEventCreation);

    ActionPrerequisite getActionPrerequisite();

    ActionPerform getActionPerform();

    ActionEventCreation<BeforeActionEvent> getBeforeActionEventCreation();

    ActionEventCreation<AfterActionEvent> getAfterActionEventCreation();
}
