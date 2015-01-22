package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.weapon.WeaponBasic;

public class MyOneHandedWeapon extends WeaponBasic {

    public MyOneHandedWeapon() {
        super("myOneHandedWeapon", WeaponType.ONE_HAND_ATTACK);
    }
}