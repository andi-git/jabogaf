package org.jabogaf.common.object.field;

import org.jabogaf.core.board.field.FieldConnectionObjectBasic;

public class Wall extends FieldConnectionObjectBasic {

    public Wall(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
