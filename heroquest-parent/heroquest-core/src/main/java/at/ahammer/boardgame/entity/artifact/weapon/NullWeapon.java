package at.ahammer.boardgame.entity.artifact.weapon;

import javax.enterprise.inject.spi.BeanManager;

/**
 * This weapon can not be used instead of null.
 * <p/>
 * Created by ahammer on 05.08.2014.
 */
public class NullWeapon extends Weapon {

    public NullWeapon() {
        super(WeaponType.NULL.toString(), WeaponType.NULL);
    }
}
