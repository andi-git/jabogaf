package at.ahammer.boardgame.api.behavior.move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A move is not possible.
 */
public class MoveNotPossibleException extends Exception {

    private final Set<MoveBlock> moveBlocks = new HashSet<>();

    public MoveNotPossibleException(String message) {
        super(message);
    }

    public MoveNotPossibleException(Set<MoveBlock> moveBlocks) {
        super("move is blocked by: " + new MoveBlockCollection(moveBlocks).toString());
        this.moveBlocks.addAll(moveBlocks);
    }

    public Set<MoveBlock> getMoveBlocks() {
        return Collections.unmodifiableSet(moveBlocks);
    }

    private static class MoveBlockCollection {

        private final Set<MoveBlock> moveBlocks = new HashSet<>();

        private MoveBlockCollection(Set<MoveBlock> moveBlocks) {
            this.moveBlocks.addAll(moveBlocks);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            moveBlocks.stream().forEach(moveBlock -> sb.append(moveBlock.getClass().getSimpleName() + ","));
            return sb.toString();
        }
    }
}
