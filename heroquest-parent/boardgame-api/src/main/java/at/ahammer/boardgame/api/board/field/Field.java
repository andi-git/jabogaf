package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.api.controller.PlayerController;
import at.ahammer.boardgame.api.subject.GameSubject;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A basic class for a field in the game, which represents a unit where a {@link at.ahammer.boardgame.api.subject.GameSubject}
 * can be positioned.
 * <p/>
 * A field is a {@link at.ahammer.boardgame.api.object.GameObject} and is always visible.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class Field extends GameContextBean {

    @Inject
    private BoardManager boardManager;

    @Inject
    private PlayerController playerController;

    /**
     * Create a new {@link Field}
     *
     * @param id the id of the {@link Field}
     */
    public Field(String id) {
        super(id);
    }

    /**
     * Check if the {@link Field} is connected to another {@link Field}
     *
     * @param target the {@link Field} to check if the current {@link Field} is conected to
     * @return true if both {@link Field}s are connected
     */
    public boolean isConnected(Field target) {
        return boardManager.getBoard().getLayout().isConnected(this, target);
    }

    /**
     * Get the representation of the connection between the two {@link Field}s as {@link FieldConnection}.
     * <p/>
     * If the {@link Field}s are not connected, the null-implementation of {@link FieldConnection} will be returned, not
     * null.
     *
     * @param target the other {@link Field}
     * @return the representation of the connection as {@link FieldConnection}
     */
    public FieldConnection getConnectionTo(Field target) {
        return boardManager.getBoard().getLayout().getConnection(this, target);
    }

    /**
     * Get a {@link java.util.List} of all available {@link at.ahammer.boardgame.api.subject.GameSubject}s on the
     * current {@link at.ahammer.boardgame.api.board.field.Field}. The {@link at.ahammer.boardgame.api.subject.GameSubject}s
     * are ordered by natural order.
     *
     * @return a {@link java.util.List} of {@link at.ahammer.boardgame.api.subject.GameSubject}s available on the
     * current {@link at.ahammer.boardgame.api.board.field.Field} sorted by natural order.
     */
    public List<GameSubject> getGameSubjects() {
        return playerController.getAllGameSubjects(this).stream().sorted().collect(Collectors.toList());
    }

    /**
     * Get a natural sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s where
     * the current {@link at.ahammer.boardgame.api.board.field.Field} is located.
     *
     * @return a natural sorted {@link java.util.List} of all {@link at.ahammer.boardgame.api.board.field.FieldGroup}s
     * where the current {@link at.ahammer.boardgame.api.board.field.Field} is located
     */
    public List<FieldGroup> getFieldsGroups() {
        return boardManager.getBoard().getLayout().getFieldsGroupsFor(this).stream().sorted().collect(Collectors.toList());
    }
}
