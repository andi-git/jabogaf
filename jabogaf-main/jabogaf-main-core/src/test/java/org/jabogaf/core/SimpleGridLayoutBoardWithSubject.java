package org.jabogaf.core;

import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.move.MoveBehavior;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.common.board.layout.grid.GridLayoutCreationStrategy;
import org.jabogaf.core.behavior.look.LookPathBasic;
import org.jabogaf.core.board.BoardBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.core.resource.LookPoint;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectBasic;

import javax.inject.Inject;

@GameScoped
public class SimpleGridLayoutBoardWithSubject {

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private PlayerController playerController;

    private MyGameSubject gameSubject;

    public void create(int sizeX, int sizeY, int positionX, int positionY, int resourcePoints) {
        new BoardBasic("board", new LayoutBasic("layout", new GridLayoutCreationStrategy(sizeX, sizeY,
                (x, y) -> new FieldBasic("f" + x + "" + y),
                (field1, field2) -> new FieldConnectionBasic("fc" + field1.getId() + "" + field2.getId(), field1, field2),
                LookPathBasic::new
        )));
        gameSubject = new MyGameSubject("gameSubject", getField(positionX, positionY));
        gameSubject.earn(new MovePoint(resourcePoints, FireEvent.None).asPayment());
        gameSubject.earn(new LookPoint(resourcePoints, FireEvent.None).asPayment());
        playerController.setCurrentPlayer(gameSubject);
    }

    public Field getField(int x, int y) {
        return gameContextManager.getGameContextBean(Field.class, "f" + x + "" + y);
    }

    public FieldConnection getFieldConnection(int x1, int y1, int x2, int y2) {
        return gameContextManager.getGameContextBean(FieldConnection.class, "fcf" + x1 + "" + y1 + "f" + x2 + "" + y2);
    }

    public GameSubject getGameSubject() {
        return gameSubject;
    }

    public void changeMoveBehaviorOfSubject(MoveBehavior moveBehavior) {
        gameSubject.changeMoveBehavior(moveBehavior);
    }

    public void changeLookBehaviorOfSubject(LookBehavior lookBehavior) {
        gameSubject.changeLookBehavior(lookBehavior);
    }

    public void setPositionOfSubject(Field field) {
        gameSubject.setPosition(field);
    }

    public SetterOfPosition getSetterOfPositionOfSubject() {
        return gameSubject.getSetterOfPosition();
    }

    private static class MyGameSubject extends GameSubjectBasic {

        public MyGameSubject(String id, Field position) {
            super(id, position);
        }

        @Override
        public void changeMoveBehavior(MoveBehavior moveBehavior) {
            super.changeMoveBehavior(moveBehavior);
        }

        @Override
        public void changeLookBehavior(LookBehavior lookBehavior) {
            super.changeLookBehavior(lookBehavior);
        }

        public void setPosition(Field field) {
            getSetterOfPosition().setPosition(field);
        }

        @Override
        public SetterOfPosition getSetterOfPosition() {
            return super.getSetterOfPosition();
        }
    }
}
