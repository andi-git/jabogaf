package at.ahammer.boardgame.core.artifact.shield;

import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.shield.ShieldType;
import at.ahammer.boardgame.core.artifact.ArtifactBasic;

public abstract class ShieldBasic extends ArtifactBasic implements Shield {

    private final ShieldType shieldType;

    /**
     * Create a new {@link at.ahammer.boardgame.core.artifact.shield.ShieldBasic}.
     *
     * @param id         the id
     * @param shieldType the {@link at.ahammer.boardgame.api.artifact.shield.ShieldType}
     */
    protected ShieldBasic(String id, ShieldType shieldType) {
        super(id, shieldType.getHandCount());
        this.shieldType = shieldType;
    }

    @Override
    public ShieldType getShieldType() {
        return shieldType;
    }
}
