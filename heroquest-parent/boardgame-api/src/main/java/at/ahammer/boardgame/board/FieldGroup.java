package at.ahammer.boardgame.board;

import at.ahammer.boardgame.object.GameObject;
import at.ahammer.boardgame.object.field.Field;

import java.util.HashSet;
import java.util.Set;

/**
 * A virtual group of {@link at.ahammer.boardgame.object.field.Field}s. This is e.g. important to set the visibility of
 * all grouped {@link at.ahammer.boardgame.object.field.Field}s at once.
 */
public abstract class FieldGroup extends GameObject {

    private final Set<Field> fields = new HashSet<>();

    /**
     * Create a new {@link at.ahammer.boardgame.board.FieldGroup}
     *
     * @param id the id of the {@link at.ahammer.boardgame.board.FieldGroup}
     */
    protected FieldGroup(String id) {
        super(id);
    }

    /**
     * Create a new {@link at.ahammer.boardgame.board.FieldGroup}
     *
     * @param id     the id of the {@link at.ahammer.boardgame.board.FieldGroup}
     * @param fields all {@link at.ahammer.boardgame.object.field.Field}s to add
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
     * Check if the current {@link at.ahammer.boardgame.board.FieldGroup} contains a {@link
     * at.ahammer.boardgame.object.field.Field}.
     *
     * @param field the {@link at.ahammer.boardgame.object.field.Field} to check if it contained in the {@link
     *              at.ahammer.boardgame.board.FieldGroup}
     * @return {@code true} if the {@link at.ahammer.boardgame.board.FieldGroup} contains the {@link
     * at.ahammer.boardgame.object.field.Field}
     */
    public boolean contains(Field field) {
        return fields.contains(field);
    }

    /**
     * All {@link at.ahammer.boardgame.object.field.Field}s within the {@link at.ahammer.boardgame.board.FieldGroup}
     * will be set with the assigned visibility.
     *
     * @param visible the visibility
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        fields.stream().forEach(f -> f.setVisible(visible));
    }
}
