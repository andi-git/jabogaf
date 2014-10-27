package at.ahammer.boardgame.action.event;

import at.ahammer.boardgame.action.GameActionParameter;

/**
 * The basic type for an {@link at.ahammer.boardgame.action.event.ActionEvent} fired before the {@link
 * at.ahammer.boardgame.action.GameAction} is performed.
 */
public class BeforeActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

    public BeforeActionEvent(T actionParameter) {
        super(actionParameter);
    }
}
