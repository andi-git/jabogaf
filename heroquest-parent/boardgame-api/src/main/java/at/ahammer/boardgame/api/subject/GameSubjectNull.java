package at.ahammer.boardgame.api.subject;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.field.Field;

public class GameSubjectNull extends GameSubject {

    public GameSubjectNull() {
        super("GameSubjectNull", null);
    }

    @Override
    public void move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        throw new MoveNotPossibleException("i'm a null-object");
    }

    @Override
    public boolean canMove(Field target) {
        return false;
    }

    @Override
    public void look(Field target) throws LookNotPossibleException {
        throw new LookNotPossibleException("i'm a null-object");
    }

    @Override
    public boolean canLook(Field target) {
        return false;
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return null;
    }

    @Override
    public LookBehavior getLookBehavior() {
        return null;
    }

    @Override
    public void changeMoveBehavior(MoveBehavior moveBehavior) {

    }

    @Override
    public void changeLookBehavior(LookBehavior lookBehavior) {

    }
}
