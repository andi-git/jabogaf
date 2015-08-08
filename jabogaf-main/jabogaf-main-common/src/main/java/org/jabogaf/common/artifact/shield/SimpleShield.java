package org.jabogaf.common.artifact.shield;

import org.jabogaf.api.artifact.shield.ShieldType;
import org.jabogaf.core.artifact.shield.ShieldBasic;

public class SimpleShield extends ShieldBasic {

    public SimpleShield() {
        this("Simple Shield");
    }

    public SimpleShield(String id) {
        super(id, ShieldType.ONE_HAND_DEFENSE);
    }

}
