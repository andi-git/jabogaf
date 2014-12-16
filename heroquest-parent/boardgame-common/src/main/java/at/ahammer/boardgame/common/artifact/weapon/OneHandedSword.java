package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

public class OneHandedSword extends Weapon {

    public OneHandedSword() {
        this("One Handed Sword");
    }

    public OneHandedSword(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }
}
