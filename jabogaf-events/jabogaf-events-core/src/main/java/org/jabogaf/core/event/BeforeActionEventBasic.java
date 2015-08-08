package org.jabogaf.core.event;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.event.BeforeActionEvent;

public class BeforeActionEventBasic<T extends GameActionParameter> extends ActionEventBasic<T> implements BeforeActionEvent<T> {

    public BeforeActionEventBasic(T actionParameter) {
        super(actionParameter);
    }
}
