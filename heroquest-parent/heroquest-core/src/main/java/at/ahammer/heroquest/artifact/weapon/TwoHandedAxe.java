package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class TwoHandedAxe extends Weapon {

    public TwoHandedAxe() {
        this("Two Handed Axe");
    }

    public TwoHandedAxe(String name) {
        super(name, WeaponType.TWO_HAND_ATTACK);
    }
}
