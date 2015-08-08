package org.jabogaf.common.artifact.weapon;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class TwoHandedAxe extends WeaponBasic {

    public TwoHandedAxe() {
        this("Two Handed Axe");
    }

    public TwoHandedAxe(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
