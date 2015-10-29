package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.behavior.move.MoveBlock;
import org.jabogaf.api.behavior.move.MoveUnableToEnd;
import org.jabogaf.core.behavior.move.MoveBehaviorBasic;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@MoveBehaviorType(MoveBehaviorCommon.class)
public class MoveBehaviorCommon extends MoveBehaviorBasic {

    @Inject
    @Any
    private Instance<MoveBlockForMoveBehaviorCommon> moveBlockForMoveBehaviorCommonList;

    @Inject
    @Any
    private Instance<MoveUnableToEndForMoveBehaviorCommon> moveUnableToEndForMoveBehaviorCommonList;

    @Override
    protected Set<MoveBlock> fillMoveBlocks() {
        Set<MoveBlock> moveBlocks = new HashSet<>();
        for (MoveBlockForMoveBehaviorCommon moveBlockForMoveBehaviorCommon : moveBlockForMoveBehaviorCommonList) {
            moveBlocks.add(moveBlockForMoveBehaviorCommon);
        }
        return moveBlocks;
    }

    @Override
    public Set<MoveUnableToEnd> fillMoveUnableToEnds() {
        Set<MoveUnableToEnd> moveUnableToEnds = new HashSet<>();
        for (MoveUnableToEndForMoveBehaviorCommon moveUnableToEndForMoveBehaviorCommon : moveUnableToEndForMoveBehaviorCommonList) {
            moveUnableToEnds.add(moveUnableToEndForMoveBehaviorCommon);
        }
        return moveUnableToEnds;
    }
}
