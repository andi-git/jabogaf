package at.ahammer.boardgame.api.artifact.weapon;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * A weapon cast an {@link at.ahammer.boardgame.api.artifact.Artifact} that can be used to attack of defend. A {@link
 * at.ahammer.boardgame.api.subject.GameSubject} can hold it in a hand.
 * <p/>
 * Every {@link Weapon} relates to a {@link WeaponType}.
 */
public abstract class Weapon extends Artifact {

    private final WeaponType weaponType;

    /**
     * Create a new {@link Weapon}.
     *
     * @param id         the id
     * @param weaponType the {@link WeaponType}
     */
    protected Weapon(String id, WeaponType weaponType) {
        super(id, weaponType.getHandCount());
        this.weaponType = weaponType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
