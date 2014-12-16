package at.ahammer.boardgame.core.artifact.shield;

import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;

/**
 * This shield can not be used instead of null.
 */
public class NullShield extends Shield {

    public NullShield() {
        super(ShieldType.NULL.toString() + System.nanoTime(), ShieldType.NULL);
    }
}
