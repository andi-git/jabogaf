package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class TwoHandedSword extends Weapon {

    public TwoHandedSword(BeanManager beanManager) {
        this(beanManager, "Two Handed Sword");
    }

    public TwoHandedSword(BeanManager beanManager, String name) {
        super(beanManager, name, WeaponType.TWO_HAND_ATTACK);
    }
}
