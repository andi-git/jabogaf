package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.GameActionParameter;
import at.ahammer.boardgame.api.action.event.BeforeActionEvent;

public class BeforeActionEventBasic<T extends GameActionParameter> extends ActionEventBasic<T> implements BeforeActionEvent<T> {

    public BeforeActionEventBasic(T actionParameter) {
        super(actionParameter);
    }
}
