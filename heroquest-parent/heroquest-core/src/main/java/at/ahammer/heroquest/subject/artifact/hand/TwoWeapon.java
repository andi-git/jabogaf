package at.ahammer.heroquest.subject.artifact.hand;

import at.ahammer.boardgame.entity.artifact.Artifact;
import at.ahammer.boardgame.entity.artifact.ArtifactCast;
import at.ahammer.boardgame.entity.artifact.HandCount;
import at.ahammer.boardgame.entity.artifact.weapon.Weapon;
import at.ahammer.boardgame.entity.artifact.weapon.WeaponType;
import at.ahammer.boardgame.entity.subject.GameSubject;
import at.ahammer.boardgame.entity.subject.artifact.hand.ArtifactHandStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by andreas on 26.07.14.
 */
@ApplicationScoped
public class TwoWeapon extends ArtifactHandStrategy {

    @Inject
    private ArtifactCast<Weapon> artifactCast;

    @Override
    protected boolean canHandle(Artifact artifact) {
        return artifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
    }

    @Override
    protected void addArtifactToHand(Artifact artifact, GameSubject person) {
        // FIXME create better change-strategy
        if (person.getLeftHand() != null && person.getLeftHand().getHandCount() == HandCount.TWO || person.getRightHand() != null && person.getRightHand().getHandCount() == HandCount.TWO) {
            person.setLeftHand(null);
            person.setRightHand(null);
        }
        if (person.getRightHand() == null) {
            person.setRightHand(artifact);
        } else {
            if (person.getRightHand() instanceof Weapon && ((Weapon) person.getRightHand()).getWeaponType() == WeaponType.TWO_HAND_ATTACK) {
                person.setRightHand(null);
            } else {
                person.setLeftHand(person.getRightHand());
            }
            person.setRightHand(artifact);
        }
    }

}
