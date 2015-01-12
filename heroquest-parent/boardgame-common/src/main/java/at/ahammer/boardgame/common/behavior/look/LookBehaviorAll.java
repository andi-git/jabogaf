package at.ahammer.boardgame.common.behavior.look;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.look.LookBehaviorType;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

/**
 * This implementation of {@link at.ahammer.boardgame.api.behavior.look.LookBehavior} can look to every {@link
 * at.ahammer.boardgame.api.board.field.Field} of the {@link at.ahammer.boardgame.api.board.layout.Layout} and can be used
 * everywhere.
 */
@ApplicationScoped
@LookBehaviorType(LookBehaviorAll.class)
public class LookBehaviorAll implements LookBehavior {

    @Inject
    private BoardManager boardManager;

    @Override
    public boolean canLook(Field position, Field target) {
        return true;
    }

    @Override
    public Set<Field> getLookableFields(Field position) {
        return boardManager.getFields();
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return true;
    }
}
