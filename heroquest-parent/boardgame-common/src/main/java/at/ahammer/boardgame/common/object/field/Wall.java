package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

public class Wall extends FieldConnectionObject {

    public Wall(String id) {
        super(id);
    }

    @Override
    public boolean canMove(MoveBehavior moveBehavior) {
        return false;
    }

    @Override
    public boolean canLook(LookBehavior lookBehavior) {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
