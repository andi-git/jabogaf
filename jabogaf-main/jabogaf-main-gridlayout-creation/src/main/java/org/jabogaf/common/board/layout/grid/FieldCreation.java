package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.board.field.Field;

/**
 * Created by andreas on 10.11.15.
 */
@FunctionalInterface
public interface FieldCreation {

    Field create(int x, int y);
}
