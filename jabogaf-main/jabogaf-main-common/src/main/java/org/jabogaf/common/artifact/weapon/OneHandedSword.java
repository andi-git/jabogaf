package org.jabogaf.common.artifact.weapon;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class OneHandedSword extends WeaponBasic {

    public OneHandedSword() {
        this("One Handed Sword");
    }

    public OneHandedSword(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }
}
