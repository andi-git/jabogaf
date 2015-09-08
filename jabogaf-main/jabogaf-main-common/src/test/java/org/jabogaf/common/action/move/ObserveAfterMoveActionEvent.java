package org.jabogaf.common.action.move;

import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.common.event.move.AfterMoveActionEvent;

import javax.enterprise.event.Observes;

@GameScoped
public class ObserveAfterMoveActionEvent {

    private boolean observe = false;

    public void observeMyBeforeActionEvent(@Observes AfterMoveActionEvent myBeforeActionEvent) {
        observe = true;
    }

    public boolean wasObserved() {
        return observe;
    }
}
