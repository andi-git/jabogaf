package org.jabogaf.common.artifact.weapon;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class OneHandedAxe extends WeaponBasic {

    public OneHandedAxe() {
        this("One Handed Axe");
    }

    public OneHandedAxe(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }

}
