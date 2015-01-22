package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.cdi.GameContextBean;

import java.util.HashSet;
import java.util.Set;

/**
 * A virtual group of {@link Field}s. This is e.g. important to set the visibility of
 * all grouped {@link Field}s at once.
 */
public interface FieldGroup extends GameContextBean {

    void add(Field field);

    void clear();

    Set<Field> getFields();

    /**
     * Check if the current {@link FieldGroup} contains a {@link
     * Field}.
     *
     * @param field the {@link Field} to check if it contained in the {@link
     *              FieldGroup}
     * @return {@code true} if the {@link FieldGroup} contains the {@link
     * Field}
     */
    boolean contains(Field field);
}
