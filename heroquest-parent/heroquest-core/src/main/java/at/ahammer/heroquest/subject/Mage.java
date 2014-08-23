package at.ahammer.heroquest.subject;

import at.ahammer.heroquest.subject.artifact.hand.OneWeapon;
import at.ahammer.heroquest.subject.artifact.hand.OneWeaponOneShield;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class Mage extends Hero {

    public Mage() {
        this("Mage");
    }

    public Mage(String id) {
        super(id);
        addHandStrategy(fromGameContext(OneWeapon.class));
        addHandStrategy(fromGameContext(OneWeaponOneShield.class));
    }
}
