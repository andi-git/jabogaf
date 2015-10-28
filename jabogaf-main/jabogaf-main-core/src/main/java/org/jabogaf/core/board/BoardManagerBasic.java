package org.jabogaf.core.board;

import org.jabogaf.api.board.Board;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;
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
    public Set<GameObject> getAllGameObjectsOnFieldConnection(Field leftHand, Field rightHand) {
        Set<GameObject> result = new HashSet<>();
        // TODO this is ugly: a check if the board is available should not be necessary (only important for move-test)
        if (gameContextManager.isGameContextBeanAvailable(Board.class)) {
            result.addAll(getBoard().getLayout().getAllGameObjectsOnFieldConnection(leftHand, rightHand));
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
    public Set<GameObject<? extends ContainsGameObjects>> getAllGameObjects() {
        Set<GameObject<? extends ContainsGameObjects>> gameObjects = new HashSet<>();
        Set<GameObject> gameContextBeans = gameContextManager.getGameContextBeans(GameObject.class);
        for (GameObject gameObject : gameContextBeans) {
            gameObjects.add(gameObject);
        }
        return Collections.unmodifiableSet(gameObjects);
    }

    @Override
    public Set<GameObject<? extends ContainsGameObjects>> getAllGameObjects(Field field) {
        return Collections.unmodifiableSet(
                getAllGameObjects().stream()
                        .filter(go -> go.getPosition() != null && go.getPosition().equals(field))
                        .collect(Collectors.toSet()));
    }

    @Override
    public Set<GameObject> getAllGameObjects(FieldConnection fieldConnection) {
        return Collections.unmodifiableSet(getAllGameObjects().stream().filter(go -> go.getPosition().equals(fieldConnection)).collect(Collectors.toSet()));
    }

    @Override
    public Set<LayoutActionImpact> getAllActionImpacts(Field field) {
        Set<LayoutActionImpact> layoutActionImpacts = new HashSet<>();
        layoutActionImpacts.addAll(getAllGameObjects(field));
        layoutActionImpacts.addAll(getAllGameSubjects(field));
        return Collections.unmodifiableSet(layoutActionImpacts);
    }
}
