package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
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

    private final Set<MoveUnableToEnd> moveUnableToEnds = new HashSet<>();

    @Inject
    private MoveBlockDoor moveBlockDoor;

    @Inject
    private MoveBlockWall moveBlockWall;

    @Inject
    private MoveBlockGameObjectOnField moveBlockGameObjectOnField;

    @Inject
    private MoveBlockGameSubjectOnField moveBlockGameSubjectOnField;

    @Inject
    private MoveUnableToEndGameSubjectOnField moveUnableToEndGameSubjectOnField;

    @Inject
    private MoveUnableToEndGameObjectOnField moveUnableToEndGameObjectOnField;

    @PostConstruct
    private void init() {
        moveBlocks.add(moveBlockDoor);
        moveBlocks.add(moveBlockWall);
        moveBlocks.add(moveBlockGameObjectOnField);
        moveBlocks.add(moveBlockGameSubjectOnField);
        moveUnableToEnds.add(moveUnableToEndGameSubjectOnField);
        moveUnableToEnds.add(moveUnableToEndGameObjectOnField);
    }

    @Override
    public Set<MoveBlock> getMoveBlocks() {
        return moveBlocks;
    }

    @Override
    public Set<MoveUnableToEnd> getMoveUnableToEnd() {
        return moveUnableToEnds;
    }
}
