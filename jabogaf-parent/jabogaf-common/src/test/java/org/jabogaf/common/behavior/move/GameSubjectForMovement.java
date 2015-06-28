package org.jabogaf.common.behavior.move;

import org.jabogaf.api.behavior.move.CanMoveReport;
import org.jabogaf.api.behavior.move.MoveBehavior;
import org.jabogaf.api.behavior.move.MoveBehaviorType;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.core.behavior.move.CanMoveReportBasic;
import org.jabogaf.core.subject.GameSubjectBasic;

import javax.inject.Inject;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class GameSubjectForMovement extends GameSubjectBasic {

    @Inject
    @MoveBehaviorType(MoveBehaviorCommon.class)
    private MoveBehaviorCommon moveBehavior;

    private Field position;

    public GameSubjectForMovement(String id, Field position) {
        super(id, position);
        this.position = position;
    }

    @Override
    public CanMoveReport canMove(Field target) {
        return new CanMoveReportBasic.CanMoveReportBuilder().buildDefault();
    }

    @Override
    public SetterOfPosition getSetterOfPosition() {
        return (position) -> {
            this.position = position;
        };
    }

    @Override
    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    @Override
    public Field getPosition() {
        return this.position;
    }
}
