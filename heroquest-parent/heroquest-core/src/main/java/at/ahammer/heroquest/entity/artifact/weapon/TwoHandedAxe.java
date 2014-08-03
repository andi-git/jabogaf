package at.ahammer.heroquest.entity.artifact.weapon;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 * Created by andreas on 26.07.14.
 */
@RequestScoped
public class TwoHandedAxe extends Weapon {

    @PostConstruct
    public void init() {
        setName("Two Handed Axe");
        setType(WeaponType.TWO_HAND_ATTACK);
    }
}
