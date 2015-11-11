package org.jabogaf.common.behavior.look;

import org.jabogaf.api.behavior.look.*;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.core.behavior.look.CanLookReportBasic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * This implementation of {@link org.jabogaf.api.behavior.look.LookBehavior} can look to every {@link
 * org.jabogaf.api.board.field.Field} of the {@link org.jabogaf.api.board.layout.Layout} and can be used everywhere.
 */
@ApplicationScoped
@LookBehaviorType(LookBehaviorAll.class)
public class LookBehaviorAll implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public CanLookReport canLook(Lookable lookable, Field target, ResourceHolder resourceHolder) {
        return new CanLookReportBasic.Builder().buildDefault();
    }

    @Override
    public List<Field> getLookableFields(Lookable lookable, ResourceHolder resourceHolder) {
        return Collections.unmodifiableList(new ArrayList<>(boardManager.getFields()));
    }

    @Override
    public Set<LookBlock> getLookBlocks() {
        return new HashSet<>();
    }

    @Override
    public Set<LookBlock> checkLookBlocks(Lookable lookable, Field target) {
        return new HashSet<>();
    }
}
