package org.jabogaf.core.board.field;

import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.api.controller.PlayerController;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.resource.Resource;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;
import org.jabogaf.core.resource.MovePoint;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A basic class for a field in the game, which represents a unit where a {@link org.jabogaf.api.subject.GameSubject}
 * can be positioned.
 * <p>
 * A field is a {@link org.jabogaf.api.object.GameObject} and is always visible.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldBasic extends GameContextBeanBasic<Field> implements Field {

    @Inject
    private BoardManager boardManager;

    @Inject
    private PlayerController playerController;

    private Set<Field> connectedFieldsCache = new HashSet<>();

    private Resource movePoint = new MovePoint(1);

    /**
     * Create a new {@link org.jabogaf.core.board.field.FieldBasic}
     *
     * @param id the id of the {@link org.jabogaf.core.board.field.FieldBasic}
     */
    public FieldBasic(String id) {
        super(id);
    }

    @Override
    public boolean isConnected(Field target) {
        return boardManager.getBoard().getLayout().isConnected(this, target);
    }

    @Override
    public FieldConnection getConnectionTo(Field target) {
        return boardManager.getBoard().getLayout().getConnection(this, target).orElseGet(FieldConnectionNull::new);
    }

    @Override
    public Set<FieldConnection> getFieldConnections() {
        return boardManager.getBoard().getLayout().getFieldConnections(this);
    }

    @Override
    public List<GameSubject> getGameSubjects() {
        return boardManager.getAllGameSubjects().stream().filter(gs -> equals(gs.getPosition())).sorted().collect(Collectors.toList());
    }

    @Override
    public List<GameObject<? extends ContainsGameObjects>> getGameObjects() {
        return boardManager.getAllGameObjects(this).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<FieldGroup> getFieldsGroups() {
        return boardManager.getBoard().getLayout().getFieldsGroupsFor(this).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Set<Field> getConnectedFields() {
        if (connectedFieldsCache.isEmpty()) {
            connectedFieldsCache.addAll(boardManager.getBoard().getLayout().getConnectedFields(this));
        }
        return connectedFieldsCache;
    }

    @Override
    public Resource movementCost() {
        return movePoint;
    }

    @Override
    public void setMovementCost(Resource movePoint) {
        this.movePoint = movePoint;
    }
}
