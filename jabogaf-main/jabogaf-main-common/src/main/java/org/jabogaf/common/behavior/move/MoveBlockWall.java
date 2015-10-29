package org.jabogaf.common.behavior.move;

import org.jabogaf.common.object.field.Wall;
import org.jabogaf.core.behavior.move.MoveIsBlockedByType;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MoveBlockWall extends MoveIsBlockedByType<Wall> implements MoveBlockForMoveBehaviorCommon {

    @Override
    protected Class<Wall> type() {
        return Wall.class;
    }
}
