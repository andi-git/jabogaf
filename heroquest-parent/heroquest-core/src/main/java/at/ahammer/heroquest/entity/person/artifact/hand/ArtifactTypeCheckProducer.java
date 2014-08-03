package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.artifact.weapon.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class ArtifactTypeCheckProducer {

    @Produces
    public ArtifactTypeCheck<Weapon> getWeaponTypeCheck() {
        return (Artifact artifact) -> (artifact instanceof Weapon) ? ((Weapon) artifact) : null;
    }
}
