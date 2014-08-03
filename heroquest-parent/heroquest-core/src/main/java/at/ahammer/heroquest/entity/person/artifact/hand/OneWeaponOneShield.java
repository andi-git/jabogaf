package at.ahammer.heroquest.entity.person.artifact.hand;

import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.artifact.weapon.Weapon;
import at.ahammer.heroquest.entity.artifact.weapon.WeaponType;
import at.ahammer.heroquest.entity.person.GameSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class OneWeaponOneShield extends ArtifactHandStrategy {

    @Inject
    private ArtifactTypeCheck<Weapon> artifactTypeCheck;

    @Override
    protected boolean canHandle(Artifact artifact) {
        Weapon weapon = artifactTypeCheck.is(artifact);
        return weapon.getType() == WeaponType.ONE_HAND_ATTACK;
    }

    @Override
    protected void addArtifactToHand(Artifact artifact, GameSubject person) {
        person.setRightHand(artifact);
    }
}
