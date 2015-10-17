package org.jabogaf.common.object.field;

import org.jabogaf.core.object.GameObjectBasic;

public class Wall extends GameObjectBasic {

    public Wall(String id) {
        super(id);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
