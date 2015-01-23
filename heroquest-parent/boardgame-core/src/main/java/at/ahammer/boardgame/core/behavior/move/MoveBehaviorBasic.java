package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.resource.MovePoint;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class MoveBehaviorBasic implements MoveBehavior {

    @Inject
    private PlayerController playerController;

    @Inject
    private BoardManager boardManager;

    @Inject
    private MovePointCollector movePointCollector;

    @Override
    public boolean canMove(Moveable moveable, Field target, ResourceHolder resourceHolder) {
        return checkMoveBlock(moveable, target, getMoveBlocks()) && checkResources(moveable, target, resourceHolder);
    }

    @Override
    public Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        if (!moveable.getPosition().isConnected(target)) {
            throw new FieldsNotConnectedException(moveable.getPosition(), target);
        }
        if (canMove(moveable, target, resourceHolder)) {
            resourceHolder.pay(movePointCollector.collect(moveable, target).asPayment());
            setterOfPosition.setPosition(target);
            return target;
        } else {
            if (!checkResources(moveable, target, resourceHolder)) {
                throw new NotEnoughResourceException(movePointCollector.collect(moveable, target).asPayment(), resourceHolder.amountOf(MovePoint.class));
            } else {
                throw new MoveNotPossibleException(getMoveBlocks().stream().filter(moveBlock -> moveBlock.blocks(moveable, target)).collect(Collectors.toSet()));
            }
        }
    }

    @Override
    public abstract Set<Field> getMovableFields(Moveable moveable, ResourceHolder resourceHolder);

    @Override
    public Set<Field> getMoveableFieldsForCurrent() {
        return getMovableFields(playerController.getCurrentPlayer(), playerController.getCurrentPlayer());
    }

    @Override
    public boolean canBeUsedOnLayout() {
        return canBeUsedOn(boardManager.getBoard().getLayout());
    }

    protected boolean checkResources(Moveable moveable, Field target, ResourceHolder resourceHolder) {
        if (resourceHolder == null) {
            return false;
        }
        return resourceHolder.canPay(movePointCollector.collect(moveable, target).asPayment());
    }

    protected boolean checkMoveBlock(Moveable moveable, Field target, Set<MoveBlock> moveBlocks) {
        if (moveable == null || target == null) {
            return false;
        }
        if (moveBlocks == null || moveBlocks.isEmpty()) {
            return true;
        }
        return !moveBlocks.stream().anyMatch(moveBlock -> moveBlock.blocks(moveable, target));
    }

}
