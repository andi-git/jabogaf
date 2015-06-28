package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.CanMoveReport;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.MovePoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanMoveReportBasic implements CanMoveReport {

    private final Resource moveCost;

    private final Resource maxPayment;

    private final Set<MoveBlock> moveBlocks = new HashSet<>();

    public CanMoveReportBasic(Resource moveCost, Resource maxPayment, Set<MoveBlock> moveBlocks) {
        this.moveCost = moveCost == null ? new MovePoint(0) : moveCost;
        this.maxPayment = maxPayment == null ? new MovePoint(0) : maxPayment;
        this.moveBlocks.addAll(moveBlocks);
    }

    @Override
    public boolean isPossible() {
        return canPay() && !isBlocked();
    }

    @Override
    public boolean canPay() {
        return maxPayment.greaterEquals(moveCost);
    }

    @Override
    public Resource moveCost() {
        return moveCost;
    }

    @Override
    public Resource maxPayment() {
        return maxPayment;
    }

    @Override
    public boolean isBlocked() {
        return !moveBlocks.isEmpty();
    }

    @Override
    public Set<MoveBlock> moveBlocks() {
        return Collections.unmodifiableSet(moveBlocks);
    }

    public static class CanMoveReportBuilder {

        private Resource cost;

        private Resource maxPayment;

        private Set<MoveBlock> moveBlocks = new HashSet<>();

        public CanMoveReportBuilder setCost(Resource cost) {
            this.cost = cost;
            return this;
        }

        public CanMoveReportBuilder setMaxPayment(Resource maxPayment) {
            this.maxPayment = maxPayment;
            return this;
        }

        public CanMoveReportBuilder setMoveBlocks(Set<MoveBlock> moveBlocks) {
            this.moveBlocks.clear();
            this.moveBlocks.addAll(moveBlocks);
            return this;
        }

        public CanMoveReport build() {
            return new CanMoveReportBasic(cost, maxPayment, moveBlocks);
        }

        public CanMoveReport buildDefault() {
            return new CanMoveReportBasic(new MovePoint(0), new MovePoint(0), new HashSet<>());
        }

        public CanMoveReport buildNull() {
            Set<MoveBlock> moveBlocks = new HashSet<>();
            moveBlocks.add((moveable, target) -> false);
            return new CanMoveReportBasic(new MovePoint(Integer.MAX_VALUE), new MovePoint(0), moveBlocks);
        }
    }
}
