package org.jabogaf.core.artifact.weapon;

import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;

/**
 * This weapon can not be used instead of null.
 */
public class NullWeapon extends WeaponBasic {

    public NullWeapon() {
        super(WeaponType.NULL.toString() + randomId(), WeaponType.NULL);
    }
}
