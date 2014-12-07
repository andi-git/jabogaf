package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.cdi.GameScoped;

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
