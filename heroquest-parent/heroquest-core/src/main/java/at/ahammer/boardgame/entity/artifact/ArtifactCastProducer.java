package at.ahammer.boardgame.entity.artifact;

import at.ahammer.boardgame.entity.artifact.weapon.NullWeapon;
import at.ahammer.boardgame.entity.artifact.weapon.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Producer-class for all concrete {@link ArtifactCast}s.
 * <p/>
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactCastProducer {

    @Inject
    private BeanManager beanManager;

    @Produces
    public ArtifactCast<Weapon> getWeaponCast() {
        return (Artifact artifact) -> (artifact instanceof Weapon) ? ((Weapon) artifact) : new NullWeapon(beanManager);
    }
}
