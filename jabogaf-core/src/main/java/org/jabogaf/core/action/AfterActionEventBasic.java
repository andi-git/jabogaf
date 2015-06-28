package org.jabogaf.core.action;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.action.event.AfterActionEvent;

public class AfterActionEventBasic<T extends GameActionParameter> extends ActionEventBasic<T> implements AfterActionEvent<T> {

    public AfterActionEventBasic(T actionParameter) {
        super(actionParameter);
    }
}
