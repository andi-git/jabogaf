package at.ahammer.boardgame.api.action.event;

import at.ahammer.boardgame.api.action.GameActionParameter;

/**
 * This is the basic-class for an event fired within a {@link at.ahammer.boardgame.api.action.GameAction}.
 */
public interface ActionEvent<T extends GameActionParameter> {

    T getActionParameter();
}