package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.cdi.GameContextBean;

import javax.inject.Inject;

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

    /**
     * Create a new {@link Field}
     *
     * @param id the id of the {@link Field}
     */
    public Field(String id) {
        super(id);
    }

    /**
     * Check if the {@link Field} is connected to another {@link
     * Field}
     *
     * @param target the {@link Field} to check if the current {@link
     *               Field} is conected to
     * @return true if both {@link Field}s are connected
     */
    public boolean isConnected(Field target) {
        return boardManager.getBoard().getLayout().isConnected(this, target);
    }

    /**
     * Get the representation of the connection between the two {@link Field}s as
     * {@link FieldConnection}.
     * <p/>
     * If the {@link Field}s are not connected, the null-implementation {@link
     * FieldConnectionNull} will be returned, not null.
     *
     * @param target the other {@link Field}
     * @return the representation of the connection as {@link FieldConnection}
     */
    public FieldConnection getConnectionTo(Field target) {
        return boardManager.getBoard().getLayout().getConnection(this, target);
    }
}
