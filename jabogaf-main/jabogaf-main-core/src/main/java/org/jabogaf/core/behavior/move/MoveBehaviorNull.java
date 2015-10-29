package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.gamecontext.GameScoped;

import java.util.HashSet;
import java.util.Set;

@GameScoped
@MoveBehaviorType(MoveBehaviorNull.class)
public class MoveBehaviorNull extends MoveBehaviorBasic {

    @Override
    protected Set<MoveUnableToEnd> fillMoveUnableToEnds() {
        return new HashSet<>();
    }

    @Override
    protected Set<MoveBlock> fillMoveBlocks() {
        return new HashSet<>();
    }
}
