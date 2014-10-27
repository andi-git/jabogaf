package at.ahammer.boardgame.action.event;

import at.ahammer.boardgame.action.GameActionParameter;

/**
 * The basic type for an {@link at.ahammer.boardgame.action.event.ActionEvent} fired after the {@link
 * at.ahammer.boardgame.action.GameAction} is performed.
 */
public class AfterActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

    public AfterActionEvent(T actionParameter) {
        super(actionParameter);
    }
}
