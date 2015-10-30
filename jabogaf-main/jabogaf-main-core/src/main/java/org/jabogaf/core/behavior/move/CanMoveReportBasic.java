package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.CanMoveReport;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.board.field.FieldNull;
import org.jabogaf.core.resource.MovePoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanMoveReportBasic implements CanMoveReport {

    private final Resource moveCost;

    private final Resource maxPayment;

    private final Set<MoveBlock> moveIsBlockedBy = new HashSet<>();

    private final Set<MoveUnableToEnd> moveIsUnableToEndBy = new HashSet<>();

    private final Field source;

    private final Field target;

    private CanMoveReportBasic(Field source, Field target, Resource moveCost, Resource maxPayment, Set<MoveBlock> moveIsBlockedBy, Set<MoveUnableToEnd> moveIsUnableToEndBy) {
        this.source = source;
        this.target = target;
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

    @Override
    public Field getSource() {
        return source;
    }

    @Override
    public Field getTarget() {
        return target;
    }

    public static class Builder {

        private Resource cost;

        private Resource maxPayment;

        private Set<MoveBlock> moveIsBlockedBy = new HashSet<>();

        private Set<MoveUnableToEnd> moveIsUnableToEndBy = new HashSet<>();

        private Field source;

        private Field target;

        public Builder setCost(Resource cost) {
            this.cost = cost;
            return this;
        }

        public Builder setMaxPayment(Resource maxPayment) {
            this.maxPayment = maxPayment;
            return this;
        }

        public Builder setMoveIsBlockedBy(Set<MoveBlock> moveIsBlockedBy) {
            this.moveIsBlockedBy.clear();
            this.moveIsBlockedBy.addAll(moveIsBlockedBy);
            return this;
        }

        public Builder setMoveIsUnableToEndBy(Set<MoveUnableToEnd> moveIsUnableToEndBy) {
            this.moveIsUnableToEndBy.clear();
            this.moveIsUnableToEndBy.addAll(moveIsUnableToEndBy);
            return this;
        }

        public Builder setSource(Field source) {
            this.source = source;
            return this;
        }

        public Builder setTarget(Field target) {
            this.target = target;
            return this;
        }

        public CanMoveReport build() {
            return new CanMoveReportBasic(source, target, cost, maxPayment, moveIsBlockedBy, moveIsUnableToEndBy);
        }

        public CanMoveReport buildDefault() {
            return new CanMoveReportBasic(new FieldNull(), new FieldNull(), new MovePoint(0), new MovePoint(0), new HashSet<>(), new HashSet<>());
        }

        public CanMoveReport buildNull() {
            Set<MoveBlock> moveBlocks = new HashSet<>();
            moveBlocks.add((moveable, target) -> false);
            return new CanMoveReportBasic(new FieldNull(), new FieldNull(), new MovePoint(Integer.MAX_VALUE), new MovePoint(0), moveBlocks, moveIsUnableToEndBy);
        }
    }
}
