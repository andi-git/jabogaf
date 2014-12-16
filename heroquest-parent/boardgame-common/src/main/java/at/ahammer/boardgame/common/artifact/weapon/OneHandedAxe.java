package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

public class OneHandedAxe extends Weapon {

    public OneHandedAxe() {
        this("One Handed Axe");
    }

    public OneHandedAxe(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }

}
