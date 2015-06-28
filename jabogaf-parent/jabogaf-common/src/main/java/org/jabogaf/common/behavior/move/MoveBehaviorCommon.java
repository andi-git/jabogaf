package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.core.behavior.move.MoveBehaviorBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@MoveBehaviorType(MoveBehaviorCommon.class)
public class MoveBehaviorCommon extends MoveBehaviorBasic {

    private final Set<MoveBlock> moveBlocks = new HashSet<>();

    @Inject
    private MoveBlockDoor moveBlockDoor;

    @Inject
    private MoveBlockWall moveBlockWall;

    @PostConstruct
    private void init() {
        moveBlocks.add(moveBlockDoor);
        moveBlocks.add(moveBlockWall);
    }

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return moveBlocks;
    }
}
