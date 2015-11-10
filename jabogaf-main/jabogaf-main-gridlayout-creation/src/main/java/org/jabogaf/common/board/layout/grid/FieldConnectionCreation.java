package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.board.field.FieldConnection;

@FunctionalInterface
public interface FieldConnectionCreation {

    FieldConnection create(Field field1, Field field2);
}
