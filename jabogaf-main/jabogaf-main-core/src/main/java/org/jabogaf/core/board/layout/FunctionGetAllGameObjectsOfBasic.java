package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.FunctionGetAllGameObjectsOf;
import org.jabogaf.api.board.layout.FunctionGetConnection;
import org.jabogaf.api.object.GameObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class FunctionGetAllGameObjectsOfBasic implements FunctionGetAllGameObjectsOf {

    @Inject
    private FunctionGetConnection functionGetConnection;

    @Override
    public Set<GameObject> getAllGameObjectsOf(Set<FieldConnection> fieldConnections, Field leftHand, Field rightHand) {
        Set<GameObject> fieldConnectionObjects = new HashSet<>();
        if (fieldConnections != null) {
            fieldConnectionObjects.addAll(functionGetConnection.getConnection(fieldConnections, leftHand, rightHand).getGameObjects());
        }
        return fieldConnectionObjects;
    }
}
