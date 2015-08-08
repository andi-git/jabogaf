package org.jabogaf.core.action;

import org.jabogaf.api.action.ActionNotPossibleException;
import org.jabogaf.api.action.GameActionLifecycle;
import org.jabogaf.api.action.GameActionPreferences;
import org.jabogaf.api.event.AfterActionEvent;
import org.jabogaf.api.event.BeforeActionEvent;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Basic implementation of {@link org.jabogaf.api.action.GameActionLifecycle}
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
