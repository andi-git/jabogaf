package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.*;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.resource.ResourceHolder;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.behavior.move.MoveBehaviorBasic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This implementation of {@link at.ahammer.boardgame.api.behavior.move.MoveBehavior} can move to every {@link
 * at.ahammer.boardgame.api.board.field.Field} of the {@link at.ahammer.boardgame.api.board.layout.Layout} and can be used
 * everywhere.
 */
@ApplicationScoped
@MoveBehaviorType(MoveBehaviorAll.class)
public class MoveBehaviorAll extends MoveBehaviorBasic {

    @Inject
    private BoardManager boardManager;

    @Override
    public Set<Field> getMovableFields(Moveable moveable, ResourceHolder resourceHolder) {
        return boardManager.getFields();
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return true;
    }

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return Collections.unmodifiableSet(new HashSet<>());
    }

}
