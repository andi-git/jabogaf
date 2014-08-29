package at.ahammer.heroquest.object.field;

import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.object.field.FieldGroup;

import java.util.Set;

/**
 * Created by andreas on 8/14/14.
 */
public class Room extends FieldGroup {

    public Room(String id, Set<Field> fields) {
        super(id, fields);
    }
}
