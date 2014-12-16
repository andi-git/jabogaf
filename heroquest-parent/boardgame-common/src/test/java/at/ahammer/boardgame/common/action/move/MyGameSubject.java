package at.ahammer.boardgame.common.action.move;

import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.subject.GameSubjectNull;

public class MyGameSubject extends GameSubjectNull {

    public MyGameSubject(String id, Field position) {
        super(id, position);
    }

    @Override
    public Field move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        setPosition(target);
        return target;
    }

    @Override
    public boolean canMove(Field target) {
        return true;
    }
}
