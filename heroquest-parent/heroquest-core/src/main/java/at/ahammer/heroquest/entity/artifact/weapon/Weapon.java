package at.ahammer.heroquest.entity.artifact.weapon;

import at.ahammer.heroquest.entity.artifact.Artifact;

/**
 * Created by andreas on 26.07.14.
 */
public class Weapon extends Artifact {

    private WeaponType type;

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }
}
