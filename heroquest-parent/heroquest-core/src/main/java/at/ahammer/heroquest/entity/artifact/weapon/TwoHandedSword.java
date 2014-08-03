package at.ahammer.heroquest.entity.artifact.weapon;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 * Created by andreas on 26.07.14.
 */
@RequestScoped
public class TwoHandedSword extends Weapon {

    @PostConstruct
    public void init() {
        setName("Two Handed Sword");
        setType(WeaponType.TWO_HAND_ATTACK);
    }
}
