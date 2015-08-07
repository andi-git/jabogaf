package org.jabogaf.api.action.event;

import org.jabogaf.api.action.GameActionParameter;

/**
 * The basic type for an {@link ActionEvent} fired before the {@code GameAction} is performed.
 */
public interface BeforeActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

}
