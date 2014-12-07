package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.event.AfterActionEvent;

public class MyAfterActionEvent extends AfterActionEvent<MyGameActionParameter> {

    private final String originalInput;

    public MyAfterActionEvent(MyGameActionParameter actionParameter) {
        super(actionParameter);
        this.originalInput = actionParameter.getInput();
    }

    public String getOriginalInput() {
        return originalInput;
    }
}
