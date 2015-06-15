package at.ahammer.boardgame.api.board;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.api.object.GameObject;
import at.ahammer.boardgame.api.subject.GameSubject;

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
    Set<FieldConnectionObject> getAllFieldConnectionObjects(Field leftHand, Field rightHand);

    /**
     * Get all available {@link GameSubject}s, i.e. players, npcs,...
     *
     * @return all available {@link GameSubject}s
     */
    Set<GameSubject> getAllGameSubjects();

    /**
     * Get all available {@link GameSubject}s, i.e. players, npcs,... positioned a concrete {@link Field}
     *
     * @return all available {@link GameSubject}s positioned a concrete {@link Field}
     */
    Set<GameSubject> getAllGameSubjects(Field field);

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,...
     *
     * @return all available {@link GameObject}s
     */
    Set<GameObject> getAllGameObjects();

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,... positioned a concrete {@link Field}
     *
     * @return all available {@link GameObject}s positioned a concrete {@link Field}
     */
    Set<GameObject> getAllGameObjects(Field field);

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,... positioned a concrete {@link Field}
     *
     * @return all available {@link GameObject}s positioned a concrete {@link Field}
     */
    Set<GameObject> getAllGameObjects(FieldConnection fieldConnection);
}
