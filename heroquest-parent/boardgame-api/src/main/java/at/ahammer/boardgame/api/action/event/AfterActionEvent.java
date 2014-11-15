package at.ahammer.boardgame.api.action.event;

import at.ahammer.boardgame.api.action.GameActionParameter;

/**
 * The basic type for an {@link ActionEvent} fired after the {@link
 * at.ahammer.boardgame.api.action.GameAction} is performed.
 */
public class AfterActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

    public AfterActionEvent(T actionParameter) {
        super(actionParameter);
    }
}
