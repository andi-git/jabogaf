package org.jabogaf.core.action;

import org.jabogaf.api.action.event.AfterActionEvent;

public class MyAfterActionEvent extends AfterActionEventBasic<MyGameActionParameter> {

    private final String originalInput;

    public MyAfterActionEvent(MyGameActionParameter actionParameter) {
        super(actionParameter);
        this.originalInput = actionParameter.getInput();
    }

    public String getOriginalInput() {
        return originalInput;
    }
}
