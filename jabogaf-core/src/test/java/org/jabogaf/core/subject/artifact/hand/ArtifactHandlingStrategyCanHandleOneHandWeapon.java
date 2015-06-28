package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ArtifactHandlingStrategyCanHandleOneHandWeapon extends ArtifactHandlingStrategyBasic {

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
            return weaponArtifactCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
        };
    }
}
