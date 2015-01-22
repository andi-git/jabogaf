package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.core.board.field.FieldConnectionObjectBasic;

public class Wall extends FieldConnectionObjectBasic {

    public Wall(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
