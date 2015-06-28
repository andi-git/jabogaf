package org.jabogaf.api.action.event;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.action.GameAction;

/**
 * This is the basic-class for an event fired within a {@link GameAction}.
 */
public interface ActionEvent<T extends GameActionParameter> {

    T getActionParameter();
}