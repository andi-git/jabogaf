package org.jabogaf.api.behavior.move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A move is not possible.
 */
public class MoveNotPossibleException extends RuntimeException {

    private final Set<MoveBlock> moveBlocks = new HashSet<>();

    private final Set<MoveUnableToEnd> moveUnableToEnds = new HashSet<>();

    public MoveNotPossibleException(String message) {
        super(message);
    }

    public MoveNotPossibleException() {
        super("move is not possible");
    }

    public MoveNotPossibleException(CanMoveReport canMoveReport) {
        super("move is blocked by: " +
                new SetToString<>(canMoveReport.moveBlocks()).toString() + "; " +
                new SetToString<>(canMoveReport.moveUnableToEnd()).toString());
        this.moveBlocks.addAll(canMoveReport.moveBlocks());
        this.moveUnableToEnds.addAll(canMoveReport.moveUnableToEnd());
    }

    public Set<MoveBlock> getMoveBlocks() {
        return Collections.unmodifiableSet(moveBlocks);
    }

    public Set<MoveUnableToEnd> getMoveUnableToEnd() {
        return Collections.unmodifiableSet(moveUnableToEnds);
    }

    private static class SetToString<T> {

        private final Set<T> set = new HashSet<>();

        private SetToString(Set<T> set) {
            this.set.addAll(set);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            set.stream().forEach(moveBlock -> sb.append(moveBlock).append(","));
            return sb.toString();
        }
    }
}
