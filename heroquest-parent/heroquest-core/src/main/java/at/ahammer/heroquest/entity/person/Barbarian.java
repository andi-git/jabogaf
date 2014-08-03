package at.ahammer.heroquest.entity.person;

import at.ahammer.heroquest.entity.person.artifact.hand.OneWeapon;
import at.ahammer.heroquest.entity.person.artifact.hand.OneWeaponOneShield;
import at.ahammer.heroquest.entity.person.artifact.hand.TwoHandedWeapon;
import at.ahammer.heroquest.entity.person.artifact.hand.TwoWeapon;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@RequestScoped
public class Barbarian extends Hero {

    @Inject
    private OneWeapon oneWeapon;

    @Inject
    private OneWeaponOneShield oneWeaponOneShield;

    @Inject
    private TwoHandedWeapon twoHandedWeapon;

    @Inject
    private TwoWeapon twoWeapon;

    @PostConstruct
    public void init() {
        setName("Barbarian");
        addHandStrategy(twoHandedWeapon); // FIXME: must be before OneWeaponOneShield
        addHandStrategy(twoWeapon);
        addHandStrategy(oneWeapon);
        addHandStrategy(oneWeaponOneShield);
    }
}
