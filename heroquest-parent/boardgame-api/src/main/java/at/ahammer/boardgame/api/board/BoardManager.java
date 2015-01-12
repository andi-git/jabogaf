package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;

import java.util.Set;

/**
 * Grant's access to the current {@link Board}
 */
public interface BoardManager {

    /**
     * Get the current {@link Board}
     *
     * @return the current {@link Board}
     */
    Board getBoard();

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.Field}s of the {@link Board} specified by the {@link
     * at.ahammer.boardgame.api.board.layout.Layout}.
     *
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.api.board.field.Field}s of the {@link Board}
     * specified by the {@link at.ahammer.boardgame.api.board.layout.Layout}
     */
    Set<Field> getFields();

    /**
     * Get all {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}s of a current {@link
     * at.ahammer.boardgame.api.board.field.FieldConnection} defined by the two {@link
     * at.ahammer.boardgame.api.board.field.Field}s.
     *
     * @param leftHand  the one {@link at.ahammer.boardgame.api.board.field.Field} to get all {@link
     *                  at.ahammer.boardgame.api.board.field.FieldConnectionObject}s of
     * @param rightHand the other {@link at.ahammer.boardgame.api.board.field.Field} to get all {@link
     *                  at.ahammer.boardgame.api.board.field.FieldConnectionObject}s of
     * @return a {@link java.util.Set} of {@link at.ahammer.boardgame.api.object.GameObject}s of the assigned {@link
     * at.ahammer.boardgame.api.board.field.Field}
     */
    Set<FieldConnectionObject> getAllFieldConectionObjects(Field leftHand, Field rightHand);
}
