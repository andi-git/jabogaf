package at.ahammer.boardgame.action;

import at.ahammer.boardgame.action.event.AfterActionEvent;
import at.ahammer.boardgame.action.event.BeforeActionEvent;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Basic implementation of {@link at.ahammer.boardgame.action.GameActionLifecycle}
 */
@ApplicationScoped
public class GameActionLifecycleBasic implements GameActionLifecycle {

    @Inject
    private Event<BeforeActionEvent> beforeActionEvent;

    @Inject
    private Event<AfterActionEvent> afterActionEvent;

    @Inject
    private Logger log;

    @Override
    public void perform(GameActionPreferences gameActionPreferences) throws ActionNotPossibleException {
        log.info("perform action {}", this);
        log.info("fire event {}", gameActionPreferences.getBeforeActionEventCreation());
        beforeActionEvent.fire(gameActionPreferences.getBeforeActionEventCreation().create());
        try {
            log.info("check prerequisites");
            gameActionPreferences.getActionPrerequisite().checkPrerequisite();
            log.info("perform action");
            gameActionPreferences.getActionPerform().perform();
        } catch (ActionNotPossibleException e) {
            log.warn("catch exception {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warn("catch exception {}", e.getMessage());
            throw new ActionNotPossibleException(e);
        }
        log.info("fire event {}", gameActionPreferences.getAfterActionEventCreation());
        afterActionEvent.fire(gameActionPreferences.getAfterActionEventCreation().create());
    }
}
