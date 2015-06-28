package org.jabogaf.core.artifact.shield;

import org.jabogaf.api.artifact.shield.Shield;
import org.jabogaf.api.artifact.shield.ShieldType;
import org.jabogaf.core.artifact.ArtifactBasic;

public abstract class ShieldBasic extends ArtifactBasic implements Shield {

    private final ShieldType shieldType;

    /**
     * Create a new {@link org.jabogaf.core.artifact.shield.ShieldBasic}.
     *
     * @param id         the id
     * @param shieldType the {@link org.jabogaf.api.artifact.shield.ShieldType}
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
