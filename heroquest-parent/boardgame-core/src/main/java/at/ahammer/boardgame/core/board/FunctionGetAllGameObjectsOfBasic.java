package at.ahammer.boardgame.core.board;

import at.ahammer.boardgame.api.board.FunctionGetAllGameObjectsOf;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

import java.util.HashSet;
import java.util.Set;

public class FunctionGetAllGameObjectsOfBasic implements FunctionGetAllGameObjectsOf {

    @Override
    public Set<FieldConnectionObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field field) {
        Set<FieldConnectionObject> gameObjects = new HashSet<>();
        fieldConnections.stream().filter((fc) -> {
            return fc.getLeftHand().equals(field) || fc.getRightHand().equals(field);
        }).forEach((fc) -> {
            gameObjects.addAll(fc.getObjectsOnConnection());
        });
        return gameObjects;
    }
}
