package at.ahammer.boardgame.api.action.event;

import at.ahammer.boardgame.api.action.GameActionParameter;

/**
 * The basic type for an {@link ActionEvent} fired before the {@link
 * at.ahammer.boardgame.api.action.GameAction} is performed.
 */
public class BeforeActionEvent<T extends GameActionParameter> extends ActionEvent<T> {

    public BeforeActionEvent(T actionParameter) {
        super(actionParameter);
    }
}