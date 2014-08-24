package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.artifact.weapon.Weapon;
import at.ahammer.boardgame.artifact.weapon.WeaponType;

/**
 * Created by andreas on 26.07.14.
 */
public class OneHandedAxe extends Weapon {

    public OneHandedAxe() {
        this("One Handed Axe");
    }

    public OneHandedAxe(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }

}
