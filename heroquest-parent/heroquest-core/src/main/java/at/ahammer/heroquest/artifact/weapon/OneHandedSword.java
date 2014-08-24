package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.artifact.weapon.Weapon;
import at.ahammer.boardgame.artifact.weapon.WeaponType;

/**
 * Created by andreas on 26.07.14.
 */
public class OneHandedSword extends Weapon {

    public OneHandedSword() {
        this("One Handed Sword");
    }

    public OneHandedSword(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }
}
