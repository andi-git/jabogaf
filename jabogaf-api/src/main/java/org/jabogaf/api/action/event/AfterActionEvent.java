package org.jabogaf.api.action.event;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.action.GameAction;

/**
 * The basic type for an {@link ActionEvent} fired after the {@link
 * GameAction} is performed.
 */
public interface AfterActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

}
