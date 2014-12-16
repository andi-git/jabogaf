package at.ahammer.boardgame.api.artifact.shield;

import at.ahammer.boardgame.api.artifact.Artifact;

/**
 * A shield used to protect against attacks. A {@link
 * at.ahammer.boardgame.api.subject.GameSubject} can hold it in a hand.
 * <p/>
 * Every {@link Shield} relates to a {@link ShieldType}.
 */
public abstract class Shield extends Artifact {

    private final ShieldType shieldType;

    /**
     * Create a new {@link Shield}.
     *
     * @param id         the id
     * @param shieldType the {@link ShieldType}
     */
    protected Shield(String id, ShieldType shieldType) {
        super(id, shieldType.getHandCount());
        this.shieldType = shieldType;
    }

    public ShieldType getShieldType() {
        return shieldType;
    }
}
