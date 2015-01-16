package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.CanMoveStrategy;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.behavior.move.Moveable;
import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class CanMoveStrategyDefault implements CanMoveStrategy {

    @Override
    public boolean canMove(Moveable moveable, Field target, Set<MoveBlock> moveBlocks) {
        if (moveable == null || target == null) {
            return false;
        }
        if (moveBlocks == null || moveBlocks.isEmpty()) {
            return true;
        }
        return !moveBlocks.stream().anyMatch(moveBlock -> moveBlock.blocks(moveable, target));
    }
}
