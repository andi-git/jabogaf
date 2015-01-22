package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.GameActionParameter;
import at.ahammer.boardgame.api.action.event.ActionEvent;

public abstract class ActionEventBasic<T extends GameActionParameter> implements ActionEvent<T> {

    private final T actionParameter;

    public ActionEventBasic(T actionParameter) {
        this.actionParameter = actionParameter;
    }

    @Override
    public T getActionParameter() {
        return actionParameter;
    }
}