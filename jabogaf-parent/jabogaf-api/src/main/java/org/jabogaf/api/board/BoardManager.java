package org.jabogaf.api.board;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.field.FieldConnectionObject;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.api.subject.GameSubject;

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
     * Get all {@link Field}s of the {@link Board}.
     *
     * @return a {@link Set} of {@link Field}s of the {@link Board}
     */
    Set<Field> getFields();

    /**
     * Get all {@link FieldConnectionObject}s of a current {@link FieldConnection} defined by the two {@link Field}s.
     *
     * @param leftHand  the one {@link Field} to get all {@link FieldConnectionObject}s of
     * @param rightHand the other {@link Field} to get all {@link FieldConnectionObject}s of
     * @return a {@link Set} of {@link GameObject}s of the assigned {@link Field}
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
