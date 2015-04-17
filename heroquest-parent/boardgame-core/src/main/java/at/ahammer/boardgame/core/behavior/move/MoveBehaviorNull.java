package at.ahammer.boardgame.core.behavior.move;

import at.ahammer.boardgame.api.behavior.move.MoveBehaviorType;
import at.ahammer.boardgame.api.behavior.move.MoveBlock;
import at.ahammer.boardgame.api.cdi.GameScoped;

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

}
