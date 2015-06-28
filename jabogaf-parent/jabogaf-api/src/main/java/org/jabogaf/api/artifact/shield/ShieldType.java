package org.jabogaf.api.artifact.shield;

import org.jabogaf.api.artifact.HandCount;

/**
 * The type of a {@link Shield}.
 */
public enum ShieldType {

    /**
     * This ca be used instead of null.
     */
    NULL(HandCount.NULL),
    /**
     * Needs one hand and be used to protect against an attack.
     */
    ONE_HAND_DEFENSE(HandCount.ONE);

    private final HandCount handCount;

    ShieldType(HandCount handCount) {
        this.handCount = handCount;
    }

    public HandCount getHandCount() {
        return handCount;
    }
}
