package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.weapon.WeaponBasic;

public class TwoHandedSword extends WeaponBasic {

    public TwoHandedSword() {
        this("Two Handed Sword");
    }

    public TwoHandedSword(String id) {
        super(id, WeaponType.TWO_HAND_ATTACK);
    }
}
