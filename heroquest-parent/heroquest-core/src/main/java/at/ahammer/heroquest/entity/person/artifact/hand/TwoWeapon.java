package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.artifact.weapon.WeaponType;
import at.ahammer.heroquest.entity.person.GameSubject;
import at.ahammer.heroquest.entity.artifact.weapon.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class TwoWeapon extends ArtifactHandStrategy {

    @Inject
    private ArtifactTypeCheck<Weapon> artifactTypeCheck;

    @Override
    protected boolean canHandle(Artifact artifact) {
        Weapon weapon = artifactTypeCheck.is(artifact);
        return weapon.getType() == WeaponType.ONE_HAND_ATTACK;
    }

    @Override
    protected void addArtifactToHand(Artifact artifact, GameSubject person) {
        if (person.getRightHand() == null) {
            person.setRightHand(artifact);
        } else {
            if (person.getRightHand() instanceof Weapon && ((Weapon) person.getRightHand()).getType() == WeaponType.TWO_HAND_ATTACK) {
                person.setRightHand(null);
            } else {
                person.setLeftHand(person.getRightHand());
            }
            person.setRightHand(artifact);
        }
    }

}
