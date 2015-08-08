package org.jabogaf.core.event;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.event.ActionEvent;

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