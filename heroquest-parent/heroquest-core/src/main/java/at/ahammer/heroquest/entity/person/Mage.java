package at.ahammer.heroquest.entity.person;

import at.ahammer.heroquest.entity.person.artifact.hand.OneWeapon;
import at.ahammer.heroquest.entity.person.artifact.hand.OneWeaponOneShield;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@RequestScoped
public class Mage extends Hero {

    @Inject
    private OneWeapon oneWeapon;

    @Inject
    private OneWeaponOneShield oneWeaponOneShield;

    @PostConstruct
    public void init() {
        setName("Mage");
        addHandStrategy(oneWeapon);
        addHandStrategy(oneWeaponOneShield);
    }
}
