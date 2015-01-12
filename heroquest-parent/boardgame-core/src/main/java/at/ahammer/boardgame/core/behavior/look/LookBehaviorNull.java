package at.ahammer.boardgame.core.behavior.look;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBehaviorType;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
@LookBehaviorType(LookBehaviorNull.class)
public class LookBehaviorNull implements LookBehavior {

    @Override
    public boolean canLook(Field position, Field target) {
        return false;
    }

    @Override
    public Set<Field> getLookableFields(Field position) {
        return Collections.unmodifiableSet(new HashSet<>());
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return false;
    }
}
