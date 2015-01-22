package at.ahammer.boardgame.api.action.event;

import at.ahammer.boardgame.api.action.GameActionParameter;

/**
 * The basic type for an {@link ActionEvent} fired after the {@link
 * at.ahammer.boardgame.api.action.GameAction} is performed.
 */
public interface AfterActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

}
