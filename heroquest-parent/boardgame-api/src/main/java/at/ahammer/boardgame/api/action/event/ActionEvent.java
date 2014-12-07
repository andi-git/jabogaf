package at.ahammer.boardgame.api.action.event;

import at.ahammer.boardgame.api.action.GameActionParameter;

/**
 * This is the basic-class for an event fired within a {@link at.ahammer.boardgame.api.action.GameAction}.
 */
public abstract class ActionEvent<T extends GameActionParameter> {

    private final T actionParameter;

    public ActionEvent(T actionParameter) {
        this.actionParameter = actionParameter;
    }

    public T getActionParameter() {
        return actionParameter;
    }
}