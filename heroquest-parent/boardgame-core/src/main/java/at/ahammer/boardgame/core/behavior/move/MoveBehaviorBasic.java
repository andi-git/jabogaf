package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.resource.NotEnoughResourceException;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.resource.MovePoint;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class MoveBehaviorBasic implements MoveBehavior {

    @Inject
    private PlayerController playerController;

    @Inject
    private MovePointCollector movePointCollector;

    @Inject
    private MoveableFields moveableFields;

    @Override
    public CanMoveReport canMove(Moveable moveable, Field target, ResourceHolder resourceHolder) {
        if (moveable == null || target == null || resourceHolder == null) {
            return new CanMoveReportBasic.CanMoveReportBuilder().buildNull();
        }
        return new CanMoveReportBasic.CanMoveReportBuilder().
                setCost(movePointCollector.collect(moveable, target)).
                setMaxPayment(resourceHolder.get(MovePoint.class)).
                setMoveBlocks(checkMoveBlocks(moveable, target, getMoveBlocks())).
                build();
    }

    @Override
    public boolean canMove(Moveable moveable, MovePath movePath, ResourceHolder resourceHolder) {
        if (moveable == null || movePath == null || resourceHolder == null) {
            return false;
        }
        Moveable testMoveable = moveable.cloneMoveable();
        ResourceHolder testResourceHolder = resourceHolder.cloneResourceHolder();
        boolean canMove = true;
        for (Field field : movePath.getPathFields()) {
            try {
                testMoveable.move(field, testResourceHolder);
            } catch (Exception e) {
                canMove = false;
                break;
            }
        }
        return canMove;
    }

    @Override
    public Field move(Moveable moveable, SetterOfPosition setterOfPosition, Field target, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        if (!moveable.getPosition().isConnected(target)) {
            throw new FieldsNotConnectedException(moveable.getPosition(), target);
        }
        CanMoveReport canMoveReport = canMove(moveable, target, resourceHolder);
        if (canMoveReport.isPossible()) {
            resourceHolder.pay(movePointCollector.collect(moveable, target).asPayment());
            setterOfPosition.setPosition(target);
            return target;
        } else if (!canMoveReport.canPay()) {
            throw new NotEnoughResourceException(movePointCollector.collect(moveable, target).asPayment(), resourceHolder.amountOf(MovePoint.class));
        } else if (canMoveReport.isBlocked()) {
            throw new MoveNotPossibleException(getMoveBlocks().stream().filter(moveBlock -> moveBlock.blocks(moveable, target)).collect(Collectors.toSet()));
        } else {
            throw new MoveNotPossibleException("move is not possible - unknown reason");
        }
    }

    @Override
    public Field move(Moveable moveable, SetterOfPosition setterOfPosition, MovePath movePath, ResourceHolder resourceHolder) throws FieldsNotConnectedException, MoveNotPossibleException, NotEnoughResourceException {
        if (canMove(moveable, movePath, resourceHolder)) {
            resourceHolder.pay(movePath.cost().asPayment());
            setterOfPosition.setPosition(movePath.getTarget());
            return movePath.getTarget();
        } else if (!resourceHolder.canPay(movePath.cost().asPayment())) {
            throw new NotEnoughResourceException(resourceHolder.get(MovePoint.class), movePath.cost().getAmount());
        } else {
            throw new MoveNotPossibleException();
        }
    }

    @Override
    public List<MovePath> getMovableFields(Moveable moveable, ResourceHolder resourceHolder) {
        return moveableFields.get(new MoveableFields.Parameter(moveable, resourceHolder));
    }

    @Override
    public MovePath getShortestPath(Moveable moveable, Field target, ResourceHolder resourceHolder) {
        return getMovableFields(moveable, resourceHolder).stream().filter(mp -> mp.getTarget().equals(target)).findFirst().get();
    }

    @Override
    public List<MovePath> getMoveableFieldsForCurrent() {
        return getMovableFields(playerController.getCurrentPlayer(), playerController.getCurrentPlayer());
    }

    protected boolean checkResources(Moveable moveable, Field target, ResourceHolder resourceHolder) {
        return resourceHolder != null && resourceHolder.canPay(movePointCollector.collect(moveable, target).asPayment());
    }

    protected Set<MoveBlock> checkMoveBlocks(Moveable moveable, Field target, Set<MoveBlock> moveBlocks) {
        return moveBlocks.stream().filter(moveBlock -> moveBlock.blocks(moveable, target)).collect(Collectors.toSet());
    }

    protected boolean checkMoveBlock(Moveable moveable, Field target, Set<MoveBlock> moveBlocks) {
        return !(moveable == null || target == null) && (moveBlocks == null || moveBlocks.isEmpty() || !moveBlocks.stream().anyMatch(moveBlock -> moveBlock.blocks(moveable, target)));
    }

}
