package at.ahammer.boardgame.subject;

import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.behavior.move.MoveBehavior;
import at.ahammer.boardgame.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.object.field.Field;

/**
 * Created by andreas on 08.10.14.
 */
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
