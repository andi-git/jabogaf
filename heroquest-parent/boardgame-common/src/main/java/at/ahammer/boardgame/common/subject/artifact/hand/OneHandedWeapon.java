package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyConcrete;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.artifact.hand.CanHandleArtifactStrategy;
import at.ahammer.boardgame.api.subject.hand.Hand;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#MAIN} can use {@link at.ahammer.boardgame.api.artifact.weapon.WeaponType#ONE_HAND_ATTACK}.
 * <p/>
 * A one-handed-weapon will be added to the main-hand.
 */
@ApplicationScoped
public class OneHandedWeapon extends ArtifactHandlingStrategy {

    @Inject
    private ArtifactCast<Weapon> artifactCast;

    @Override
    public CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            return artifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK && //
                    handType == Hand.Type.MAIN;
        };
    }

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            addArtifactToHandStrategyContext.addArtifactToMainHand();
        };
    }
}