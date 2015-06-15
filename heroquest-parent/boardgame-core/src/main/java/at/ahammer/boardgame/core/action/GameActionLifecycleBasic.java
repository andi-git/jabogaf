package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.ActionNotPossibleException;
import at.ahammer.boardgame.api.action.GameActionLifecycle;
import at.ahammer.boardgame.api.action.GameActionPreferences;
import at.ahammer.boardgame.api.action.event.AfterActionEvent;
import at.ahammer.boardgame.api.action.event.BeforeActionEvent;
import at.ahammer.boardgame.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Basic implementation of {@link at.ahammer.boardgame.api.action.GameActionLifecycle}
 */
@ApplicationScoped
public class GameActionLifecycleBasic implements GameActionLifecycle {

    @Inject
    private Event<BeforeActionEvent> beforeActionEvent;

    @Inject
    private Event<AfterActionEvent> afterActionEvent;

    @Inject
    @SLF4J
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
