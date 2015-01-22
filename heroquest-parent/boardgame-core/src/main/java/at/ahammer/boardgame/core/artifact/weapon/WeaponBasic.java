package at.ahammer.boardgame.core.artifact.weapon;

import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.core.artifact.ArtifactBasic;

public abstract class WeaponBasic extends ArtifactBasic implements Weapon {

    private final WeaponType weaponType;

    /**
     * Create a new {@link at.ahammer.boardgame.core.artifact.weapon.WeaponBasic}.
     *
     * @param id         the id
     * @param weaponType the {@link at.ahammer.boardgame.api.artifact.weapon.WeaponType}
     */
    protected WeaponBasic(String id, WeaponType weaponType) {
        super(id, weaponType.getHandCount());
        this.weaponType = weaponType;
    }

    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }
}
