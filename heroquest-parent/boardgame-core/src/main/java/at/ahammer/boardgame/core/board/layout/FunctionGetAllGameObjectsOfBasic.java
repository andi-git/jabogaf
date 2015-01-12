package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.FunctionGetAllGameObjectsOf;
import at.ahammer.boardgame.api.board.layout.FunctionGetConnection;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class FunctionGetAllGameObjectsOfBasic implements FunctionGetAllGameObjectsOf {

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Override
    public Set<FieldConnectionObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field leftHand, Field rightHand) {
        Set<FieldConnectionObject> fieldConnectionObjects = new HashSet<>();
        if (fieldConnections != null) {
            fieldConnectionObjects.addAll(functionGetConnection.getConnection(fieldConnections, leftHand, rightHand).getObjectsOnConnection());
        }
        return fieldConnectionObjects;
    }
}
