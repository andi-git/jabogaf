package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldGroup extends GameObject {

    private final Set<Field> fields = new HashSet<>();

    protected FieldGroup(String id) {
        super(id);
    }

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

    public boolean contains(Field field) {
        return fields.contains(field);
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
