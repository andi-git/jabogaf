package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBlock;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.core.board.field.FieldNull;
import org.jabogaf.core.resource.LookPoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CanLookReportBasic implements CanLookReport {

    private final Field source;

    private final Field target;

    private final Resource cost;

    private final Resource maxPayment;

    private final Set<LookBlock> lookBlocks = new HashSet<>();

    private CanLookReportBasic(Field source, Field target, Resource cost, Resource maxPayment, Set<LookBlock> lookBlocks) {
        this.source = source;
        this.target = target;
        this.cost = cost;
        this.maxPayment = maxPayment;
        this.lookBlocks.addAll(lookBlocks);
    }

    @Override
    public Field getSource() {
        return source;
    }

    @Override
    public Field getTarget() {
        return target;
    }

    @Override
    public Resource cost() {
        return cost;
    }

    @Override
    public Resource maxPayment() {
        return maxPayment;
    }

    @Override
    public boolean isPossible() {
        return !isBlocked();
    }

    @Override
    public boolean isBlocked() {
        return !lookBlocks.isEmpty();
    }

    @Override
    public Set<LookBlock> lookBlocks() {
        return Collections.unmodifiableSet(lookBlocks);
    }

    public static class Builder {

        private Field source;

        private Field target;

        private Resource cost;

        private Resource maxPayment;

        private Set<LookBlock> lookBlocks = new HashSet<>();

        public Builder setSource(Field source) {
            this.source = source;
            return this;
        }

        public Builder setTarget(Field target) {
            this.target = target;
            return this;
        }

        public Builder setCost(Resource cost) {
            this.cost = cost;
            return this;
        }

        public Builder setMaxPayment(Resource maxPayment) {
            this.maxPayment = maxPayment;
            return this;
        }

        public Builder setLookBlocks(Set<LookBlock> lookBlocks) {
            this.lookBlocks.clear();
            this.lookBlocks.addAll(lookBlocks);
            return this;
        }

        public CanLookReport build() {
            return new CanLookReportBasic(source, target, cost, maxPayment, lookBlocks);
        }

        public CanLookReport buildDefault() {
            return new CanLookReportBasic(new FieldNull(), new FieldNull(), new LookPoint(0), new LookPoint(0), new HashSet<>());
        }

        public CanLookReport buildNull() {
            Set<LookBlock> lookBlocks = new HashSet<>();
            lookBlocks.add((moveable, target) -> false);
            return new CanLookReportBasic(new FieldNull(), new FieldNull(), new LookPoint(Integer.MAX_VALUE), new LookPoint(0), lookBlocks);
        }
    }
}