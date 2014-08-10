package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class OneHandedSword extends Weapon {

    public OneHandedSword() {
        this("One Handed Sword");
    }

    public OneHandedSword(String name) {
        super(name, WeaponType.ONE_HAND_ATTACK);
    }
}
