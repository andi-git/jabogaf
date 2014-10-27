package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.object.field.Field;

import java.util.Set;

/**
 * Grant's access to the current {@link at.ahammer.boardgame.board.Board}
 */
public interface BoardManager {

    /**
     * Get the current {@link at.ahammer.boardgame.board.Board}
     *
     * @return the current {@link at.ahammer.boardgame.board.Board}
     */
    Board getBoard();

    /**
     * Get all {@link at.ahammer.boardgame.object.field.Field}s of the {@link at.ahammer.boardgame.board.Board}
     * specified by the {@link at.ahammer.boardgame.board.Layout}.
     *
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.object.field.Field}s of the {@link
     * at.ahammer.boardgame.board.Board} specified by the {@link at.ahammer.boardgame.board.Layout}
     */
    Set<Field> getFields();

    /**
     * Get all {@link at.ahammer.boardgame.object.GameObject}s of a current {@link at.ahammer.boardgame.object.field.Field}
     *
     * @param field the {@link at.ahammer.boardgame.object.field.Field} to get all {@link
     *              at.ahammer.boardgame.object.GameObject}s of
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.object.GameObject}s of the assigned {@link
     * at.ahammer.boardgame.object.field.Field}
     */
    Set<GameObject> getGameObjects(Field field);
}
