package at.ahammer.heroquest.subject.artifact.hand;

import at.ahammer.boardgame.artifact.Artifact;
import at.ahammer.boardgame.artifact.ArtifactCast;
import at.ahammer.boardgame.artifact.weapon.WeaponType;
import at.ahammer.boardgame.subject.GameSubject;
import at.ahammer.boardgame.artifact.weapon.Weapon;
import at.ahammer.boardgame.subject.artifact.hand.ArtifactHandStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class OneWeapon extends ArtifactHandStrategy {

    @Inject
    private ArtifactCast<Weapon> artifactCast;

    @Override
    protected boolean canHandle(Artifact artifact) {
        return artifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
    }

    @Override
    protected void addArtifactToHand(Artifact artifact, GameSubject person) {
        person.setRightHand(artifact);
    }
}
