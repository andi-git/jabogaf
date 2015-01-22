package at.ahammer.boardgame.core.artifact;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.shield.Shield;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.core.artifact.shield.ShieldNull;
import at.ahammer.boardgame.core.artifact.weapon.NullWeapon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Producer-class for concrete {@link at.ahammer.boardgame.api.artifact.ArtifactCast}s.
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
