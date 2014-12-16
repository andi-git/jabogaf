package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

public class TwoHandedAxe extends Weapon {

    public TwoHandedAxe() {
        this("Two Handed Axe");
    }

    public TwoHandedAxe(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
