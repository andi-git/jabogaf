package at.ahammer.boardgame.core.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

/**
 * This weapon can not be used instead of null.
 */
public class NullWeapon extends Weapon {

    public NullWeapon() {
        super(WeaponType.NULL.toString() + System.nanoTime(), WeaponType.NULL);
    }
}
