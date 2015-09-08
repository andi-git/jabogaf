package org.jabogaf.core.action;

import org.jabogaf.api.gamecontext.GameScoped;

import javax.enterprise.event.Observes;

@GameScoped
public class ObserveMyBeforeActionEvent {

    private String input;

    public void observeMyBeforeActionEvent(@Observes MyBeforeActionEvent myBeforeActionEvent) {
        this.input = myBeforeActionEvent.getActionParameter().getInput();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
