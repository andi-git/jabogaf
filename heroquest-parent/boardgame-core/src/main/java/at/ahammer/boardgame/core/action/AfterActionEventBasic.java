package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.GameActionParameter;
import at.ahammer.boardgame.api.action.event.AfterActionEvent;

public class AfterActionEventBasic<T extends GameActionParameter> extends ActionEventBasic<T> implements AfterActionEvent<T> {

    public AfterActionEventBasic(T actionParameter) {
        super(actionParameter);
    }
}
