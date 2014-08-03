package at.ahammer.heroquest.entity.artifact.weapon;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 * Created by andreas on 26.07.14.
 */
@RequestScoped
public class OneHandedAxe extends Weapon {

    @PostConstruct
    public void init() {
        setName("One Handed Axe");
        setType(WeaponType.ONE_HAND_ATTACK);
    }
}
