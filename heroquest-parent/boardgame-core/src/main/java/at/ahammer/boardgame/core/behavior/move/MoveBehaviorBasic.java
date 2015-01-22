package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.SetterOfPosition;

import javax.inject.Inject;
import java.util.Set;

/**
 * The strategy of a move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 * <p/>
 * With {@link #canMove(at.ahammer.boardgame.api.behavior.move.Moveable, at.ahammer.boardgame.api.board.field.Field)} it
 * can be checked if it is possible to move from one {@link at.ahammer.boardgame.api.board.field.Field} to another.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class MoveBehaviorBasic implements MoveBehavior {

    @Inject
    private PlayerController playerController;

    @Inject
    private BoardManager boardManager;

    @Inject
    private CanMoveStrategy canMoveStrategy;

    @Inject
    private MoveStrategy moveStrategy;

    @Override
    public boolean canMove(Moveable moveable, Field target) {
        return canMoveStrategy.canMove(moveable, target, getMoveBlocks());
    }

    @Override
    public Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target) throws FieldsNotConnectedException, MoveNotPossibleException {
        return moveStrategy.move(moveable, setterOfPosition, target, getMoveBlocks());
    }

    @Override
    public abstract Set<Field> getMovableFields(Moveable moveable);

    @Override
    public Set<Field> getMoveableFieldsForCurrent() {
        return getMovableFields(playerController.getCurrentPlayer());
    }

    @Override
    public boolean canBeUsedOnLayout() {
        return canBeUsedOn(boardManager.getBoard().getLayout());
    }
}
