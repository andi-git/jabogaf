package at.ahammer.boardgame.api.artifact.weapon;

import at.ahammer.boardgame.api.artifact.HandCount;

/**
 * The type of a {@link Weapon}.
 */
public enum WeaponType {

    /**
     * This ca be used instead of null.
     */
    NULL(HandCount.NULL),
    /**
     * Needs one hand and be used to perform an attack.
     */
    ONE_HAND_ATTACK(HandCount.ONE),
    /**
     * Needs two hands and can be used to perform an attack.
     */
    TWO_HAND_ATTACK(HandCount.TWO);

    private final HandCount handCount;

    WeaponType(HandCount handCount) {
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
