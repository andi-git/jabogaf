package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.*;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.resource.NotEnoughResourceException;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.core.resource.MovePoint;

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
        return new CanMoveReportBasic.CanMoveReportBuilder()
                .setCost(movePointCollector.collect(moveable, target))
                .setMaxPayment(resourceHolder.get(MovePoint.class))
                .setMoveIsBlockedBy(checkMoveBlocks(moveable, target))
                .setMoveIsUnableToEndBy(checkMoveUnableToEnd(moveable, target))
                .build();
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
        } else if (canMoveReport.isBlocked() || !canMoveReport.isAbleToEnd()) {
            throw new MoveNotPossibleException(canMoveReport);
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

    @Override
    public Set<MoveBlock> checkMoveBlocks(Moveable moveable, Field target) {
        return getMoveBlocks().stream()
                .filter(moveBlock -> moveBlock.blocks(moveable, target))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<MoveUnableToEnd> checkMoveUnableToEnd(Moveable moveable, Field target) {
        return getMoveUnableToEnd().stream()
                .filter(mute -> mute.unableToEnd(moveable, target))
                .collect(Collectors.toSet());
    }
}
