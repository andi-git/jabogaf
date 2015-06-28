package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class MyOneHandedWeapon extends WeaponBasic {

    public MyOneHandedWeapon() {
        super("myOneHandedWeapon", WeaponType.ONE_HAND_ATTACK);
    }
}