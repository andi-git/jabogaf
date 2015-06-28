package org.jabogaf.common.object.field;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.core.board.field.FieldGroupBasic;

import java.util.Set;

public class Room extends FieldGroupBasic {

    public Room(String id, Set<Field> fields) {
        super(id, fields);
    }
}
