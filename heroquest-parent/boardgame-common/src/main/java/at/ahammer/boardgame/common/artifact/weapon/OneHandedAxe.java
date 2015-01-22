package at.ahammer.boardgame.common.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.weapon.WeaponBasic;

public class OneHandedAxe extends WeaponBasic {

    public OneHandedAxe() {
        this("One Handed Axe");
    }

    public OneHandedAxe(String id) {
        super(id, WeaponType.ONE_HAND_ATTACK);
    }

}
