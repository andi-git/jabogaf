package org.jabogaf.common.board.layout.grid;

import org.jabogaf.api.behavior.look.LookPath;
import org.jabogaf.api.board.field.Field;

import java.util.List;

@FunctionalInterface
public interface LookPathCreation {

    LookPath create(List<Field> allFieldsInLook);
}
