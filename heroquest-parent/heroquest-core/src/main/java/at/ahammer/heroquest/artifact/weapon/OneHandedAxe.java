package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class OneHandedAxe extends Weapon {

    public OneHandedAxe(BeanManager beanManager) {
        this(beanManager, "One Handed Axe");
    }

    public OneHandedAxe(BeanManager beanManager, String name) {
        super(beanManager, name, WeaponType.ONE_HAND_ATTACK);
    }

}
