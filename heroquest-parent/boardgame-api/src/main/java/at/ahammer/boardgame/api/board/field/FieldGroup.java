package at.ahammer.boardgame.api.board.field;

import at.ahammer.boardgame.api.cdi.GameContextBean;

import java.util.HashSet;
import java.util.Set;

/**
 * A virtual group of {@link Field}s. This is e.g. important to set the visibility of
 * all grouped {@link Field}s at once.
 */
public abstract class FieldGroup extends GameContextBean {

    private final Set<Field> fields = new HashSet<>();

    /**
     * Create a new {@link FieldGroup}
     *
     * @param id the id of the {@link FieldGroup}
     */
    protected FieldGroup(String id) {
        super(id);
    }

    /**
     * Create a new {@link FieldGroup}
     *
     * @param id     the id of the {@link FieldGroup}
     * @param fields all {@link Field}s to add
     */
    protected FieldGroup(String id, Set<Field> fields) {
        super(id);
        this.fields.addAll(fields);
    }

    public void add(Field field) {
        fields.add(field);
    }

    public void clear() {
        fields.clear();
    }

    public Set<Field> getFields() {
        return fields;
    }

    /**
     * Check if the current {@link FieldGroup} contains a {@link
     * Field}.
     *
     * @param field the {@link Field} to check if it contained in the {@link
     *              FieldGroup}
     * @return {@code true} if the {@link FieldGroup} contains the {@link
     * Field}
     */
    public boolean contains(Field field) {
        return fields.contains(field);
    }
}
