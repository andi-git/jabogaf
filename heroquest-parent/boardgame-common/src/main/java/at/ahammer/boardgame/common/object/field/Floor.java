package at.ahammer.boardgame.common.object.field;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.board.field.FieldGroup;
import at.ahammer.boardgame.core.board.field.FieldGroupBasic;

import java.util.Set;

public class Floor extends FieldGroupBasic {

    public Floor(String id, Set<Field> fields) {
        super(id, fields);
    }
}
