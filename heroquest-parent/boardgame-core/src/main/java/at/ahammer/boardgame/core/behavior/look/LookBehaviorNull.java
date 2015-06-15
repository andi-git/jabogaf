package at.ahammer.boardgame.core.behavior.look;

import at.ahammer.boardgame.api.behavior.look.CanLookReport;
import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBehaviorType;
import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.board.field.Field;

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
