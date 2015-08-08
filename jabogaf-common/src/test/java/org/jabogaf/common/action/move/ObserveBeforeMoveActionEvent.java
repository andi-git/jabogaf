package org.jabogaf.common.action.move;

import org.jabogaf.api.cdi.GameScoped;
import org.jabogaf.common.event.move.BeforeMoveActionEvent;

import javax.enterprise.event.Observes;

@GameScoped
public class ObserveBeforeMoveActionEvent {

    private boolean observe = false;

    public void observeMyBeforeActionEvent(@Observes BeforeMoveActionEvent myBeforeActionEvent) {
        observe = true;
    }

    public boolean wasObserved() {
        return observe;
    }
}
