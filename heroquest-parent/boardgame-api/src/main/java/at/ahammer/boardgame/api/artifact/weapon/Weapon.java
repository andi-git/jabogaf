package at.ahammer.boardgame.api.artifact.weapon;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * A weapon can be used to attack other {@link at.ahammer.boardgame.api.subject.GameSubject}s. A {@link
 * at.ahammer.boardgame.api.subject.GameSubject} can hold it in a hand.
 * <p/>
 * Every {@link Weapon} relates to a {@link WeaponType}.
 */
public interface Weapon extends Artifact {

    WeaponType getWeaponType();
}
