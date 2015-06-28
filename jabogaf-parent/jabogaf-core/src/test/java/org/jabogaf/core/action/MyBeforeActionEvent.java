package org.jabogaf.core.action;

import org.jabogaf.api.action.event.BeforeActionEvent;

public class MyBeforeActionEvent extends BeforeActionEventBasic<MyGameActionParameter> {

    private final String originalInput;

    public MyBeforeActionEvent(MyGameActionParameter actionParameter) {
        super(actionParameter);
        originalInput = actionParameter.getInput();
    }

    public String getOriginalInput() {
        return originalInput;
    }
}
