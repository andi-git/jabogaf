package org.jabogaf.core.board;

import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;
import org.jabogaf.api.gamecontext.GameContextManager;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.subject.GameSubject;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@GameScoped
public class BoardManagerBasic implements BoardManager {

    @Inject
    private GameContextManager gameContextManager;

    @Override
    public Board getBoard() {
        return gameContextManager.getGameContextBean(Board.class);
    }

    @Override
    public Set<Field> getFields() {
        return getBoard().getLayout().getFields();
    }

    @Override
    public Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand) {
        Set<FieldConnectionObject> result = new HashSet<>();
        if (getBoard() != null) {
            if (getBoard().getLayout() != null) {
                result.addAll(getBoard().getLayout().getAllFieldConnectionObjects(leftHand, rightHand));
            }
        }
        return result;
    }

    @Override
    public Set<GameSubject> getAllGameSubjects() {
        return Collections.unmodifiableSet(gameContextManager.getGameContextBeans(GameSubject.class).stream().collect(Collectors.toSet()));
    }

    @Override
    public Set<GameSubject> getAllGameSubjects(Field field) {
        return Collections.unmodifiableSet(getAllGameSubjects().stream().filter(gs -> field.equals(gs.getPosition())).collect(Collectors.toSet()));
    }

    @Override
    public Set<GameObject> getAllGameObjects() {
        return Collections.unmodifiableSet(gameContextManager.getGameContextBeans(GameObject.class).stream().collect(Collectors.toSet()));
    }

    @Override
    public Set<GameObject> getAllGameObjects(Field field) {
        return Collections.unmodifiableSet(getAllGameObjects().stream().filter(go -> go.getPosition().equals(field)).collect(Collectors.toSet()));
    }

    @Override
    public Set<GameObject> getAllGameObjects(FieldConnection fieldConnection) {
        return Collections.unmodifiableSet(getAllGameObjects().stream().filter(go -> go.getPosition().equals(fieldConnection)).collect(Collectors.toSet()));
    }
}
