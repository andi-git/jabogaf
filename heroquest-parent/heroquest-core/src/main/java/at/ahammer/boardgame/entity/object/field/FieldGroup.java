package at.ahammer.boardgame.entity.object.field;

import at.ahammer.boardgame.entity.object.GameObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreas on 8/14/14.
 */
public abstract class FieldGroup extends GameObject {

    private final Set<Field> fields = new HashSet<>();

    public void add(Field field) {
        fields.add(field);
    }

    public void clear() {
        fields.clear();
    }

    public Set<Field> getFields() {
        return fields;
    }
}
