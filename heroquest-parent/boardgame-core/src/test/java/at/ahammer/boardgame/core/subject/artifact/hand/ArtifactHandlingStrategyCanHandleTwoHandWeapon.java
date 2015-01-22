package at.ahammer.boardgame.core.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.ArtifactCast;
import at.ahammer.boardgame.api.artifact.weapon.Weapon;
import at.ahammer.boardgame.api.artifact.weapon.WeaponType;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ArtifactHandlingStrategyCanHandleTwoHandWeapon extends ArtifactHandlingStrategyBasic {

    @Inject
    private ArtifactCast<Weapon> weaponArtifactCast;

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            addArtifactToHandStrategyContext.addArtifactToMainHand();
        };
    }

    @Override
    protected CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            return weaponArtifactCast.cast(artifact).getWeaponType() == WeaponType.TWO_HAND_ATTACK;
        };
    }
}
