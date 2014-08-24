package at.ahammer.heroquest.artifact.weapon;

import at.ahammer.boardgame.artifact.weapon.Weapon;
import at.ahammer.boardgame.artifact.weapon.WeaponType;

/**
 * Created by andreas on 26.07.14.
 */
public class TwoHandedAxe extends Weapon {

    public TwoHandedAxe() {
        this("Two Handed Axe");
    }

    public TwoHandedAxe(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
