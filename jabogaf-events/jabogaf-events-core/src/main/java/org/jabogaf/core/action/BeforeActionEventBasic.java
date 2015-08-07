package org.jabogaf.core.action;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.action.event.BeforeActionEvent;

public class BeforeActionEventBasic<T extends GameActionParameter> extends ActionEventBasic<T> implements BeforeActionEvent<T> {

    public BeforeActionEventBasic(T actionParameter) {
        super(actionParameter);
    }
}
