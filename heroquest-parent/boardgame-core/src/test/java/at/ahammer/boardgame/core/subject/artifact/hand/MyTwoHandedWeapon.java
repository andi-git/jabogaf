package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.weapon.WeaponBasic;

public class MyTwoHandedWeapon extends WeaponBasic {

    public MyTwoHandedWeapon() {
        super("myTwoHandedWeapon", WeaponType.TWO_HAND_ATTACK);
    }
}