package org.jabogaf.api.board;

import org.jabogaf.api.board.field.ContainsGameObjects;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.board.layout.LayoutActionImpact;
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
     * Get all {@link GameObject}s of a current {@link FieldConnection} defined by the two {@link Field}s.
     *
     * @param leftHand  the one {@link Field} to get all {@link GameObject}s of
     * @param rightHand the other {@link Field} to get all {@link GameObject}s of
     * @return a {@link Set} of {@link GameObject}s of the assigned {@link Field}
     */
    Set<GameObject> getAllGameObjectsOnFieldConnection(Field leftHand, Field rightHand);

    /**
     * Get all available {@link GameSubject}s, i.e. players, npcs,...
     *
     * @return all available {@link GameSubject}s
     */
    Set<GameSubject> getAllGameSubjects();

    /**
     * Get all available {@link GameSubject}s, i.e. players, npcs,... positioned a concrete {@link Field}
     *
     * @param field the {@link Field} to check
     * @return all available {@link GameSubject}s positioned a concrete {@link Field}
     */
    Set<GameSubject> getAllGameSubjects(Field field);

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,...
     *
     * @return all available {@link GameObject}s
     */
    Set<GameObject<? extends ContainsGameObjects>> getAllGameObjects();

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,... positioned a concrete {@link Field}
     *
     * @param field the {@link Field} to check
     * @return all available {@link GameObject}s positioned a concrete {@link Field}
     */
    Set<GameObject<? extends ContainsGameObjects>> getAllGameObjects(Field field);

    /**
     * Get all available {@link GameObject}s, i.e. table, tree,... positioned a concrete {@link Field}
     *
     * @return all available {@link GameObject}s positioned a concrete {@link Field}
     */
    Set<GameObject> getAllGameObjects(FieldConnection fieldConnection);

    /**
     * Get all {@link LayoutActionImpact}s ({@link GameSubject}s, {@link GameObject}s) on a {@link Field}.
     *
     * @param field the {@link Field} to check
     * @return all {@link LayoutActionImpact}s ({@link GameSubject}s, {@link GameObject}s) on a {@link Field}
     */
    Set<LayoutActionImpact> getAllActionImpacts(Field field);
}
