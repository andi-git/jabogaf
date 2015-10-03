package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.CanMoveReport;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.resource.MovePoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanMoveReportBasic implements CanMoveReport {

    private final Resource moveCost;

    private final Resource maxPayment;

    private final Set<MoveBlock> moveIsBlockedBy = new HashSet<>();

    private final Set<MoveUnableToEnd> moveIsUnableToEndBy = new HashSet<>();

    public CanMoveReportBasic(Resource moveCost, Resource maxPayment, Set<MoveBlock> moveIsBlockedBy, Set<MoveUnableToEnd> moveIsUnableToEndBy) {
        this.moveCost = moveCost == null ? new MovePoint(0) : moveCost;
        this.maxPayment = maxPayment == null ? new MovePoint(0) : maxPayment;
        this.moveIsBlockedBy.addAll(moveIsBlockedBy);
        this.moveIsUnableToEndBy.addAll(moveIsUnableToEndBy);
    }

    @Override
    public boolean isPossible() {
        return canPay() && !isBlocked() && isAbleToEnd();
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
        return !moveIsBlockedBy.isEmpty();
    }

    @Override
    public Set<MoveBlock> moveBlocks() {
        return Collections.unmodifiableSet(moveIsBlockedBy);
    }

    @Override
    public boolean isAbleToEnd() {
        return moveUnableToEnd().isEmpty();
    }

    @Override
    public Set<MoveUnableToEnd> moveUnableToEnd() {
        return Collections.unmodifiableSet(moveIsUnableToEndBy);
    }

    public static class CanMoveReportBuilder {

        private Resource cost;

        private Resource maxPayment;

        private Set<MoveBlock> moveIsBlockedBy = new HashSet<>();

        private Set<MoveUnableToEnd> moveIsUnableToEndBy = new HashSet<>();

        public CanMoveReportBuilder setCost(Resource cost) {
            this.cost = cost;
            return this;
        }

        public CanMoveReportBuilder setMaxPayment(Resource maxPayment) {
            this.maxPayment = maxPayment;
            return this;
        }

        public CanMoveReportBuilder setMoveIsBlockedBy(Set<MoveBlock> moveIsBlockedBy) {
            this.moveIsBlockedBy.clear();
            this.moveIsBlockedBy.addAll(moveIsBlockedBy);
            return this;
        }

        public CanMoveReportBuilder setMoveIsUnableToEndBy(Set<MoveUnableToEnd> moveIsUnableToEndBy) {
            this.moveIsUnableToEndBy.clear();
            this.moveIsUnableToEndBy.addAll(moveIsUnableToEndBy);
            return this;
        }

        public CanMoveReport build() {
            return new CanMoveReportBasic(cost, maxPayment, moveIsBlockedBy, moveIsUnableToEndBy);
        }

        public CanMoveReport buildDefault() {
            return new CanMoveReportBasic(new MovePoint(0), new MovePoint(0), new HashSet<>(), new HashSet<>());
        }

        public CanMoveReport buildNull() {
            Set<MoveBlock> moveBlocks = new HashSet<>();
            moveBlocks.add((moveable, target) -> false);
            return new CanMoveReportBasic(new MovePoint(Integer.MAX_VALUE), new MovePoint(0), moveBlocks, moveIsUnableToEndBy);
        }
    }
}
