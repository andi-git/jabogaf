package at.ahammer.heroquest.subject;

import at.ahammer.heroquest.subject.artifact.hand.OneWeapon;
import at.ahammer.heroquest.subject.artifact.hand.OneWeaponOneShield;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class Mage extends Hero {

    public Mage(BeanManager beanManager) {
        this(beanManager, "Mage");
    }

    public Mage(BeanManager beanManager, String name) {
        super(beanManager, name);
        addHandStrategy(fromGameContext(OneWeapon.class));
        addHandStrategy(fromGameContext(OneWeaponOneShield.class));
    }
}
