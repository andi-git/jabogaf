package org.jabogaf.api.artifact.weapon;

import org.jabogaf.api.artifact.Artifact;

/**
 * A weapon can be used to attack other {@link GameSubject}s. A {@link GameSubject} can hold it in a hand.
 * <p>
 * Every {@link Weapon} relates to a {@link WeaponType}.
 */
public interface Weapon extends Artifact {

    WeaponType getWeaponType();
}
