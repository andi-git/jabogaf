package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.event.BeforeActionEvent;

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
