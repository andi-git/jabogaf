package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

public class TwoHandedSword extends Weapon {

    public TwoHandedSword() {
        this("Two Handed Sword");
    }

    public TwoHandedSword(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
