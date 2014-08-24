package at.ahammer.boardgame.artifact;

import at.ahammer.boardgame.artifact.weapon.NullWeapon;
import at.ahammer.boardgame.artifact.weapon.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Producer-class for all concrete {@link ArtifactCast}s.
 * <p/>
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactCastProducer {

    @Produces
    public ArtifactCast<Weapon> getWeaponCast() {
        return (Artifact artifact) -> (artifact instanceof Weapon) ? ((Weapon) artifact) : new NullWeapon();
    }
}
