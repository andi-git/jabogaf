package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.LookBehaviorType;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@LookBehaviorType(LookBehaviorNull.class)
public class LookBehaviorNull implements LookBehavior {

    @Override
    public CanLookReport canLook(Lookable lookable, Field target) {
        return new CanLookReportBasic();
    }

    @Override
    public Set<Field> getLookableFields(Lookable lookable) {
        return Collections.unmodifiableSet(new HashSet<>());
    }

    @Override
    public Set<MoveBlock> getLookBlocks() {
        return new HashSet<>();
    }
}
