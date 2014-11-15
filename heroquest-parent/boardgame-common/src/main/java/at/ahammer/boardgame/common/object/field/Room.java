package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldGroup;

import java.util.Set;

public class Room extends FieldGroup {

    public Room(String id, Set<Field> fields) {
        super(id, fields);
    }
}
