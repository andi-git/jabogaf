package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.api.subject.artifact.hand.ArtifactHandlingStrategy;

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
