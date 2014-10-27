package at.ahammer.boardgame.action.event;

import at.ahammer.boardgame.action.GameAction;
import at.ahammer.boardgame.action.GameActionParameter;

/**
 * This is the basic-class for an event fired within a {@link at.ahammer.boardgame.action.GameAction}.
 */
public abstract class ActionEvent<T extends GameActionParameter> {

    private final T actionParameter;

    public ActionEvent(T actionParameter) {
        this.actionParameter = actionParameter;
    }

    protected T getActionParameter() {
        return actionParameter;
    }
}