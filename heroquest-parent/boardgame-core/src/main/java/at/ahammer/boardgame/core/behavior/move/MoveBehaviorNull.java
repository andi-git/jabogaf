package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehaviorType;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.board.field.FieldNull;

import java.util.*;

@GameScoped
@MoveBehaviorType(MoveBehaviorNull.class)
public class MoveBehaviorNull implements MoveBehavior {

    @Override
    public boolean canMove(Field position, Field target) {
        return false;
    }

    @Override
    public Field move(SetterOfPosition setterOfPosition, Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        return new FieldNull();
    }

    @Override
    public Set<Field> getMovableFields(Field position) {
        return Collections.unmodifiableSet(new HashSet<>());
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return false;
    }
}
