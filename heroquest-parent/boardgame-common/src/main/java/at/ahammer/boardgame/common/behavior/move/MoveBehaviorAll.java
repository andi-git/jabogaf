package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehaviorType;
import at.ahammer.boardgame.api.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

/**
 * This implementation of {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} can move to every {@link
 * at.ahammer.boardgame.api.board.field.Field} of the {@link at.ahammer.boardgame.api.board.layout.Layout} and can be used
 * everywhere.
 */
@ApplicationScoped
@MoveBehaviorType(MoveBehaviorAll.class)
public class MoveBehaviorAll implements MoveBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean canMove(Field position, Field target) {
        return true;
    }

    @Override
    public Field move(SetterOfPosition setterOfPosition, Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        setterOfPosition.setPosition(target);
        return target;
    }

    @Override
    public Set<Field> getMovableFields(Field position) {
        return boardManager.getFields();
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return true;
    }
}
