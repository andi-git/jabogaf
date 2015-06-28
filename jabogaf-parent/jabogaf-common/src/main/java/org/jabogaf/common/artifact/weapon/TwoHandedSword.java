package org.jabogaf.common.artifact.weapon;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class TwoHandedSword extends WeaponBasic {

    public TwoHandedSword() {
        this("Two Handed Sword");
    }

    public TwoHandedSword(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
