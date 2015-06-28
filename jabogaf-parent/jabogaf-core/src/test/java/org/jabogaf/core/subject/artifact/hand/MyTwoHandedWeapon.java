package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.weapon.WeaponBasic;

public class MyTwoHandedWeapon extends WeaponBasic {

    public MyTwoHandedWeapon() {
        super("myTwoHandedWeapon", WeaponType.TWO_HAND_ATTACK);
    }
}