package org.jabogaf.core.board.field;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldGroup;
import org.jabogaf.core.gamecontext.GameContextBeanBasic;

import java.util.HashSet;
import java.util.Set;

/**
 * A virtual group of {@link org.jabogaf.api.board.field.Field}s. This is e.g. important to set the visibility of
 * all grouped {@link org.jabogaf.api.board.field.Field}s at once.
 */
public class FieldGroupBasic extends GameContextBeanBasic implements FieldGroup {

    private final Set<Field> fields = new HashSet<>();

    /**
     * Create a new {@link org.jabogaf.core.board.field.FieldGroupBasic}
     *
     * @param id the id of the {@link org.jabogaf.core.board.field.FieldGroupBasic}
     */
    protected FieldGroupBasic(String id) {
        super(id);
    }

    /**
     * Create a new {@link org.jabogaf.core.board.field.FieldGroupBasic}
     *
     * @param id     the id of the {@link org.jabogaf.core.board.field.FieldGroupBasic}
     * @param fields all {@link org.jabogaf.api.board.field.Field}s to add
     */
    protected FieldGroupBasic(String id, Set<Field> fields) {
        super(id);
        this.fields.addAll(fields);
    }

    @Override
    public void add(Field field) {
        fields.add(field);
    }

    @Override
    public void clear() {
        fields.clear();
    }

    @Override
    public Set<Field> getFields() {
        return fields;
    }

    @Override
    public boolean contains(Field field) {
        return fields.contains(field);
    }
}
