package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.LookBlock;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.core.resource.LookPoint;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
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

    @Inject
    private LookableFields lookableFields;

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
    public List<Field> getLookableFields(Lookable lookable, ResourceHolder resourceHolder) {
        return lookableFields.get(new LookableFields.Parameter(lookable, resourceHolder));
    }

    @Override
    public Set<LookBlock> getLookBlocks() {
        return lookBlocks;
    }
}
