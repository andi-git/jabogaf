package org.jabogaf.core.action;

import org.jabogaf.api.gamecontext.GameScoped;

import javax.enterprise.event.Observes;

@GameScoped
public class ObserveMyAfterActionEvent {

    private String input;

    public void observeMyAfterActionEvent(@Observes MyAfterActionEvent myAfterActionEvent) {
        this.input = myAfterActionEvent.getActionParameter().getInput();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
