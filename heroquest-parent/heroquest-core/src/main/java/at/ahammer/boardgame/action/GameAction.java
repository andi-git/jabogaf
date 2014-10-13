package at.ahammer.boardgame.action;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by andreas on 08.10.14.
 */
public abstract class GameAction {

    @Inject
    private Event<BeforeActionEvent> beforeActionEvent;

    @Inject
    private Event<AfterActionEvent> afterActionEvent;

    protected void perform(GameActionPreferences gameActionPreferences) throws ActionNotPossibleException {
        beforeActionEvent.fire(gameActionPreferences.getBeforeActionEventCreation().create());
        try {
            gameActionPreferences.getActionPrerequisite().checkPrerequisite();
            gameActionPreferences.getActionPerform().perform();
        } catch (ActionNotPossibleException e) {
            throw e;
        } catch (Exception e) {
            throw new ActionNotPossibleException(e);
        }
        afterActionEvent.fire(gameActionPreferences.getAfterActionEventCreation().create());
    }
}
