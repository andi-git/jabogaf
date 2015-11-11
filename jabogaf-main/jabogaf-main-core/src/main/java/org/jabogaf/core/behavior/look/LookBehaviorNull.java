package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.*;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.resource.ResourceHolder;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
@LookBehaviorType(LookBehaviorNull.class)
public class LookBehaviorNull implements LookBehavior {

    @Override
    public CanLookReport canLook(Lookable lookable, Field target, ResourceHolder resourceHolder) {
        return new CanLookReportBasic.Builder().buildNull();
    }

    @Override
    public List<Field> getLookableFields(Lookable lookable, ResourceHolder resourceHolder) {
        return Collections.unmodifiableList(new ArrayList<>());
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
