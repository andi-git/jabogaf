package org.jabogaf.core.behavior.move;

import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.api.gamecontext.GameScoped;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@GameScoped
@MoveBehaviorType(MoveBehaviorNull.class)
public class MoveBehaviorNull extends MoveBehaviorBasic {

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return Collections.unmodifiableSet(new HashSet<>());
    }

    @Override
    public Set<MoveUnableToEnd> getMoveUnableToEnd() {
        return Collections.unmodifiableSet(new HashSet<>());
    }

}
