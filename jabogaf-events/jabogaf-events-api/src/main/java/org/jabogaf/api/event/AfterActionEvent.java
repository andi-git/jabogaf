package org.jabogaf.api.event;

import org.jabogaf.api.action.GameActionParameter;

/**
 * The basic type for an {@link ActionEvent} fired after the {@code GameAction} is performed.
 */
public interface AfterActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

}
