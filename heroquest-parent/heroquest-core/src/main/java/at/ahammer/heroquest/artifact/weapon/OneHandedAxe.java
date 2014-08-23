package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class OneHandedAxe extends Weapon {

    public OneHandedAxe() {
        this("One Handed Axe");
    }

    public OneHandedAxe(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }

}
