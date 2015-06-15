package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.api.subject.GameSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Method;

@ApplicationScoped
public class MoveBlockGameSubjectOnField implements MoveBlock {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean blocks(Moveable moveable, Field target) {
        return false;
    }

    @Override
    public String toString() {
        return GameSubject.class.getSimpleName();
    }
}
