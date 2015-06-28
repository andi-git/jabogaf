package org.jabogaf.api.action.event;

import org.jabogaf.api.action.GameActionParameter;
import org.jabogaf.api.action.GameAction;

/**
 * The basic type for an {@link ActionEvent} fired before the {@link
 * GameAction} is performed.
 */
public interface BeforeActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

}
