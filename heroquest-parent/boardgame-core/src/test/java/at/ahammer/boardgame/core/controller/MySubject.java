package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;

public class MySubject extends GameSubject {

    public MySubject() {
        super("id", null);
    }

    @Override
    public void move(Field target) throws FieldsNotConnectedException, MoveNotPossibleException {

    }

    @Override
    public boolean canMove(Field target) {
        return false;
    }

    @Override
    public void look(Field target) throws LookNotPossibleException {

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
