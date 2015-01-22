package at.ahammer.boardgame.common.behavior.move;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehaviorType;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.SetterOfPosition;
import at.ahammer.boardgame.core.subject.GameSubjectBasic;

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
    public boolean canMove(Field target) {
        return true;
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
