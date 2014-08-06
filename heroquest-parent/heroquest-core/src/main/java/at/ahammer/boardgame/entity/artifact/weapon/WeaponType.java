package at.ahammer.boardgame.entity.artifact.weapon;

import at.ahammer.boardgame.entity.artifact.HandCount;

/**
 * The type of a {@link at.ahammer.boardgame.entity.artifact.weapon.Weapon}.
 * <p/>
 * Created by andreas on 26.07.14.
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
    TWO_HAND_ATTACK(HandCount.TWO),
    /**
     * Can be used to defend an attack.
     */
    DEFENSE(HandCount.ONE);

    private final HandCount handCount;

    WeaponType(HandCount handCount) {
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
