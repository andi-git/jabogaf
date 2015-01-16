package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehaviorType;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.common.board.layout.grid.GridLayout;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@MoveBehaviorType(MoveBehaviorCommon.class)
public class MoveBehaviorCommon extends MoveBehavior {

    @Inject
    private MoveBlockDoor moveBlockDoor;

    @Inject
    private MoveBlockWall moveBlockWall;

    private final Set<MoveBlock> moveBlocks = new HashSet<>();

    @PostConstruct
    private void init() {
        moveBlocks.add(moveBlockDoor);
        moveBlocks.add(moveBlockWall);
    }

    @Override
    public Set<Field> getMovableFields(Moveable moveable) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public boolean canBeUsedOn(Layout layout) {
        return layout instanceof GridLayout;
    }

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return moveBlocks;
    }
}
