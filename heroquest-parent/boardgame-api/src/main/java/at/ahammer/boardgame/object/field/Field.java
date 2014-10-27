package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;

/**
 * A basic class for a field in the game, which represents a unit where a {@link at.ahammer.boardgame.subject.GameSubject}
 * can be positioned.
 * <p/>
 * A field is a {@link at.ahammer.boardgame.object.GameObject} and is always visible.
 */
public abstract class Field extends GameObject {

    /**
     * Create a new {@link at.ahammer.boardgame.object.field.Field}
     *
     * @param id the id of the {@link at.ahammer.boardgame.object.field.Field}
     */
    protected Field(String id) {
        super(id);
    }

    /**
     * Check if the {@link at.ahammer.boardgame.object.field.Field} is connected to another {@link
     * at.ahammer.boardgame.object.field.Field}
     *
     * @param target the {@link at.ahammer.boardgame.object.field.Field} to check if the current {@link
     *               at.ahammer.boardgame.object.field.Field} is conected to
     * @return true if both {@link at.ahammer.boardgame.object.field.Field}s are connected
     */
    public abstract boolean isConnected(Field target);

    /**
     * Get the representation of the connection between the two {@link at.ahammer.boardgame.object.field.Field}s as
     * {@link at.ahammer.boardgame.object.field.FieldConnection}.
     * <p/>
     * If the {@link at.ahammer.boardgame.object.field.Field}s are not connected, the null-implementation {@link
     * at.ahammer.boardgame.object.field.FieldConnectionNull} will be returned, not null.
     *
     * @param target the other {@link at.ahammer.boardgame.object.field.Field}
     * @return the representation of the connection as {@link at.ahammer.boardgame.object.field.FieldConnection}
     */
    public abstract FieldConnection getConnectionTo(Field target);

    /**
     * A {@link at.ahammer.boardgame.object.field.Field} is always visible.
     *
     * @return {@code true}
     */
    @Override
    public boolean isVisible() {
        return true;
    }
}
