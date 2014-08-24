package at.ahammer.boardgame.artifact.weapon;

import at.ahammer.boardgame.artifact.Artifact;

/**
 * A weapon cast an {@link Artifact} that can be used to attack of defend. A {@link at.ahammer.boardgame.subject.GameSubject} can hold it in a hand.
 * <p/>
 * Every {@link Weapon} relates to a {@link WeaponType}.
 * <p/>
 * Created by andreas on 26.07.14.
 */
public abstract class Weapon extends Artifact {

    private final WeaponType weaponType;

    protected Weapon(String id, WeaponType weaponType) {
        super(id, weaponType.getHandCount());
        this.weaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
