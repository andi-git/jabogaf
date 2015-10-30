package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.LookBlock;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.Moveable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;
import org.jabogaf.api.gamecontext.GameContextBean;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.core.behavior.move.CanMoveReportBasic;
import org.jabogaf.core.resource.LookPoint;
import org.jabogaf.core.resource.MovePoint;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Basic implementation of {@link LookBehavior}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class LookBehaviorBasic implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Inject
    private LookPointCollector lookPointCollector;

    private Set<LookBlock> lookBlocks = new HashSet<>();

    @PostConstruct
    private void init() {
        lookBlocks = Collections.unmodifiableSet(fillLookBlocks());
    }

    protected abstract Set<LookBlock> fillLookBlocks();

    @Override
    public CanLookReport canLook(Lookable lookable, Field target, ResourceHolder resourceHolder) {
        if (lookable == null || target == null || resourceHolder == null) {
            return new CanLookReportBasic.Builder().buildNull();
        }
        return new CanLookReportBasic.Builder()
                .setCost(lookPointCollector.collect(lookable, target))
                .setMaxPayment(resourceHolder.get(LookPoint.class))
                .setLookBlocks(checkLookBlocks(lookable, target))
                .setSource(lookable.getPosition())
                .setTarget(target)
                .build();
    }

    @Override
    public Set<LookBlock> checkLookBlocks(Lookable lookable, Field target) {
        return getLookBlocks().stream()
                .filter(lookBlock -> lookBlock.blocks(lookable, target))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Field> getLookableFields(Lookable lookable, ResourceHolder resourceHolder) {
        return null;
    }
}
