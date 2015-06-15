package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.common.object.field.Wall;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MoveBlockGameObjectOnField implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        return false;
    }

    @Override
    public String toString() {
        return GameObject.class.getSimpleName();
    }
}
