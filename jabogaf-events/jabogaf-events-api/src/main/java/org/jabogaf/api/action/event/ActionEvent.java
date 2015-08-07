package org.jabogaf.api.action.event;

import org.jabogaf.api.action.GameActionParameter;

/**
 * This is the basic-class for an event fired within a {@code GameAction}.
 */
public interface ActionEvent<T extends GameActionParameter> {

    T getActionParameter();
}