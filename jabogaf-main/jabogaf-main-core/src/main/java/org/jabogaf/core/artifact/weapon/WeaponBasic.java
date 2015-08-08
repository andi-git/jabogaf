package org.jabogaf.core.artifact.weapon;

import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.artifact.ArtifactBasic;

public abstract class WeaponBasic extends ArtifactBasic implements Weapon {

    private final WeaponType weaponType;

    /**
     * Create a new {@link org.jabogaf.core.artifact.weapon.WeaponBasic}.
     *
     * @param id         the id
     * @param weaponType the {@link org.jabogaf.api.artifact.weapon.WeaponType}
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
