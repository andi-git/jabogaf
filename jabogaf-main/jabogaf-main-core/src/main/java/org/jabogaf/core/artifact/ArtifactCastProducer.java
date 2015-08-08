package org.jabogaf.core.artifact;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.shield.Shield;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.core.artifact.shield.ShieldNull;
import org.jabogaf.core.artifact.weapon.NullWeapon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Producer-class for concrete {@link org.jabogaf.api.artifact.ArtifactCast}s.
 */
@ApplicationScoped
public class ArtifactCastProducer {

    @Produces
    public ArtifactCast<Weapon> getWeaponCast() {
        return (Artifact artifact) -> (artifact instanceof Weapon) ? ((Weapon) artifact) : new NullWeapon();
    }

    @Produces
    public ArtifactCast<Shield> getShieldCast() {
        return (Artifact artifact) -> (artifact instanceof Shield) ? ((Shield) artifact) : new ShieldNull();
    }
}
