package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.api.subject.hand.Hand;
import at.ahammer.boardgame.core.subject.artifact.hand.AddArtifactToHandStrategyConcrete;
import at.ahammer.boardgame.core.subject.artifact.hand.ArtifactHandlingStrategyBasic;
import at.ahammer.boardgame.core.subject.artifact.hand.CanHandleArtifactStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#MAIN} and the {@link at.ahammer.boardgame.api.subject.hand.Hand.Type#OFF} can use {@link at.ahammer.boardgame.api.artifact.weapon.WeaponType#ONE_HAND_ATTACK}.
 * <p/>
 * A one-handed-weapon will be added to the main-hand or the off-hand.
 */
@ApplicationScoped
public class TwoOneHandedWeapons extends ArtifactHandlingStrategyBasic {

    @Inject
    private ArtifactCast<Weapon> artifactCast;

    @Override
    public CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            return artifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
        };
    }

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            if (addArtifactToHandStrategyContext.getHandType() == Hand.Type.MAIN) {
                addArtifactToHandStrategyContext.addArtifactToMainHand();
            } else if (addArtifactToHandStrategyContext.getHandType() == Hand.Type.OFF) {
                addArtifactToHandStrategyContext.addArtifactToOffHand();
            }
        };
    }
}
