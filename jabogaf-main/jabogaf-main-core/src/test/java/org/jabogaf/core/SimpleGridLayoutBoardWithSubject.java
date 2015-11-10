package org.jabogaf.core;

import org.jabogaf.api.behavior.look.LookBehavior;
import org.jabogaf.api.behavior.move.MoveBehavior;
import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.gamecontext.FireEvent;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.api.subject.SetterOfPosition;
import org.jabogaf.core.board.BoardBasic;
import org.jabogaf.core.board.field.FieldBasic;
import org.jabogaf.core.board.field.FieldConnectionBasic;
import org.jabogaf.core.board.layout.LayoutBasic;
import org.jabogaf.core.resource.LookPoint;
import org.jabogaf.core.resource.MovePoint;
import org.jabogaf.core.subject.GameSubjectBasic;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@GameScoped
public class SimpleGridLayoutBoardWithSubject {

    @Inject
    private GameContextManager gameContextManager;

    @Inject
    private PlayerController playerController;

    private Field[][] fieldsArray;

    private Map<String, Field> fields = new HashMap<>();

    private Map<String, FieldConnection> fieldConnections = new HashMap<>();

    private MyGameSubject gameSubject;

    private Board board;

    public void create(int sizeX, int sizeY, int positionX, int positionY, int resourcePoints) {
        createLayout(sizeX, sizeY);
        Layout layout = new LayoutBasic("layout", getFieldsAsSet(), getFieldConnectionsAsSet(), new HashSet<>(), new HashMap<>());
        board = new BoardBasic("board", layout);
        gameSubject = new MyGameSubject("gameSubject", getField(positionX, positionY));
        gameSubject.earn(new MovePoint(resourcePoints, FireEvent.None).asPayment());
        gameSubject.earn(new LookPoint(resourcePoints, FireEvent.None).asPayment());
        playerController.setCurrentPlayer(gameSubject);
    }

    private void createLayout(int x, int y) {
        fieldsArray = new Field[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                String id = "f" + i + "" + j;
                fieldsArray[i][j] = new FieldBasic(id);
                fields.put(id, fieldsArray[i][j]);
            }
        }
        createFieldConnections();
    }

    private void createFieldConnections() {
        int y = fieldsArray.length;
        int x = fieldsArray[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ((i + 1) < y) {
                    String id = "fc" + i + "" + j + "-" + (i + 1) + "" + j;
                    fieldConnections.put(id, new FieldConnectionBasic(id, fieldsArray[i][j], fieldsArray[i + 1][j]));
                }
                if ((j + 1) < x) {
                    String id = "fc" + i + "" + j + "-" + i + "" + (j + 1);
                    fieldConnections.put(id, new FieldConnectionBasic(id, fieldsArray[i][j], fieldsArray[i][j + 1]));
                }
            }
        }
    }

    public Field getField(int x, int y) {
        return gameContextManager.getGameContextBean(Field.class, "f" + x + "" + y);
    }

    public FieldConnection getFieldConnection(int x1, int y1, int x2, int y2) {
        return gameContextManager.getGameContextBean(FieldConnection.class, "fc" + x1 + "" + y1 + "-" + x2 + "" + y2);
    }

    public Field[][] getFieldsArray() {
        return fieldsArray;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public Map<String, FieldConnection> getFieldConnections() {
        return fieldConnections;
    }

    public Set<Field> getFieldsAsSet() {
        return fields.keySet().stream().map(fields::get).collect(Collectors.toSet());
    }

    public Set<FieldConnection> getFieldConnectionsAsSet() {
        return fieldConnections.keySet().stream().map(fieldConnections::get).collect(Collectors.toSet());
    }

    public Board getBoard() {
        return board;
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
