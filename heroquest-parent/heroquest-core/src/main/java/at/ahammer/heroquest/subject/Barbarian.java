package at.ahammer.heroquest.subject;

import at.ahammer.heroquest.subject.artifact.hand.OneWeapon;
import at.ahammer.heroquest.subject.artifact.hand.OneWeaponOneShield;
import at.ahammer.heroquest.subject.artifact.hand.TwoHandedWeapon;
import at.ahammer.heroquest.subject.artifact.hand.TwoWeapon;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 26.07.14.
 */
public class Barbarian extends Hero {

    public Barbarian() {
        this("Barbarian");
    }

    public Barbarian(String name) {
        super(name);
        addHandStrategy(fromGameContext(TwoHandedWeapon.class)); // FIXME: must be before OneWeaponOneShield
        addHandStrategy(fromGameContext(TwoWeapon.class));       // FIXME: must be beforeOneWeapon
        addHandStrategy(fromGameContext(OneWeapon.class));
        addHandStrategy(fromGameContext(OneWeaponOneShield.class));
    }
}
