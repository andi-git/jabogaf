package org.jabogaf.common.subject.artifact.hand;

import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.shield.Shield;
import org.jabogaf.api.artifact.shield.ShieldType;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.api.subject.hand.Hand;
import org.jabogaf.core.subject.artifact.hand.AddArtifactToHandStrategyConcrete;
import org.jabogaf.core.subject.artifact.hand.ArtifactHandlingStrategyBasic;
import org.jabogaf.core.subject.artifact.hand.CanHandleArtifactStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The {@link org.jabogaf.api.subject.hand.Hand.Type#MAIN} can use {@link org.jabogaf.api.artifact.weapon.WeaponType#ONE_HAND_ATTACK}.
 * <p/>
 * The {@link org.jabogaf.api.subject.hand.Hand.Type#OFF} can use {@link org.jabogaf.api.artifact.shield.ShieldType#ONE_HAND_DEFENSE}.
 * <p/>
 * A one-handed-weapon will be added to the main-hand.
 * <p/>
 * A one-handed-defense will be added to the off-hand.
 */
@ApplicationScoped
public class OneHandedWeaponAndShield extends ArtifactHandlingStrategyBasic {

    @Inject
    private ArtifactCast<Weapon> artifactWeaponCast;

    @Inject
    private ArtifactCast<Shield> artifactShieldCast;

    @Override
    public CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            boolean canHandle = false;
            if (handType == Hand.Type.MAIN) {
                canHandle = artifactWeaponCast.cast(artifact).getWeaponType() == WeaponType.ONE_HAND_ATTACK;
            } else if (handType == Hand.Type.OFF) {
                canHandle = artifactShieldCast.cast(artifact).getShieldType() == ShieldType.ONE_HAND_DEFENSE;
            }
            return canHandle;
        };
    }

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            if (addArtifactToHandStrategyContext.getArtifact() instanceof Shield) {
                addArtifactToHandStrategyContext.addArtifactToOffHand();
            } else if (addArtifactToHandStrategyContext.getArtifact() instanceof Weapon) {
                addArtifactToHandStrategyContext.addArtifactToMainHand();
            }
        };
    }
}
