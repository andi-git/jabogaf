package at.ahammer.boardgame.core.board.field;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.resource.Resource;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.cdi.GameContextBeanBasic;
import at.ahammer.boardgame.core.resource.MovePoint;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A basic class for a field in the game, which represents a unit where a {@link at.ahammer.boardgame.api.subject.GameSubject}
 * can be positioned.
 * <p/>
 * A field is a {@link at.ahammer.boardgame.api.object.GameObject} and is always visible.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldBasic extends GameContextBeanBasic implements Field {

    @Inject
    private BoardManager boardManager;

    @Inject
    private PlayerController playerController;

    private Set<Field> connectedFieldsCache = new HashSet<>();

    /**
     * Create a new {@link at.ahammer.boardgame.core.board.field.FieldBasic}
     *
     * @param id the id of the {@link at.ahammer.boardgame.core.board.field.FieldBasic}
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
        return boardManager.getBoard().getLayout().getConnection(this, target);
    }

    @Override
    public List<GameSubject> getGameSubjects() {
        return playerController.getAllGameSubjects(this).stream().sorted().collect(Collectors.toList());
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
        return new MovePoint(1);
    }
}
