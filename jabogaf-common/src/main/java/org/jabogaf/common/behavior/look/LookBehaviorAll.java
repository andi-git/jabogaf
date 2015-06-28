package org.jabogaf.common.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.look.LookBehaviorType;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.core.behavior.look.CanLookReportBasic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * This implementation of {@link org.jabogaf.api.behavior.look.LookBehavior} can look to every {@link
 * org.jabogaf.api.board.field.Field} of the {@link org.jabogaf.api.board.layout.Layout} and can be used
 * everywhere.
 */
@ApplicationScoped
@LookBehaviorType(LookBehaviorAll.class)
public class LookBehaviorAll implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public CanLookReport canLook(Lookable lookable, Field target) {
        return new CanLookReportBasic();
    }

    @Override
    public Set<Field> getLookableFields(Lookable lookable) {
        return boardManager.getFields();
    }

    @Override
    public Set<MoveBlock> getLookBlocks() {
        return new HashSet<>();
    }
}
