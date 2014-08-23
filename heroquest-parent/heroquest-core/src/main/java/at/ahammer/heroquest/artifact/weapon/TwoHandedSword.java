package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class TwoHandedSword extends Weapon {

    public TwoHandedSword() {
        this("Two Handed Sword");
    }

    public TwoHandedSword(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
