package org.jabogaf.core.artifact.shield;

import org.jabogaf.api.artifact.shield.Shield;
import org.jabogaf.api.artifact.shield.ShieldType;
import org.jabogaf.api.artifact.weapon.WeaponType;

/**
 * This shield can not be used instead of null.
 */
public class ShieldNull extends ShieldBasic {

    public ShieldNull() {
        super(ShieldType.NULL.toString() + randomId(), ShieldType.NULL);
    }
}
