package org.jabogaf.common.subject.artifact.hand;

import org.jabogaf.api.artifact.ArtifactCast;
import org.jabogaf.api.artifact.weapon.Weapon;
import org.jabogaf.api.artifact.weapon.WeaponType;
import org.jabogaf.core.subject.artifact.hand.AddArtifactToHandStrategyConcrete;
import org.jabogaf.core.subject.artifact.hand.ArtifactHandlingStrategyBasic;
import org.jabogaf.core.subject.artifact.hand.CanHandleArtifactStrategy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * The {@link org.jabogaf.api.subject.hand.Hand.Type#MAIN} can use {@link org.jabogaf.api.artifact.weapon.WeaponType#TWO_HAND_ATTACK}.
 * <p/>
 * The same two-handed-weapon will be added to the main-hand and the off-hand.
 */
@ApplicationScoped
public class TwoHandedWeapon extends ArtifactHandlingStrategyBasic {

    @Inject
    private ArtifactCast<Weapon> artifactCast;

    @Override
    public CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        return (artifact, handType) -> {
            return artifactCast.cast(artifact).getWeaponType() == WeaponType.TWO_HAND_ATTACK;
        };
    }

    @Override
    protected AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        return (addArtifactToHandStrategyContext) -> {
            addArtifactToHandStrategyContext.addArtifactToBothHands();
        };
    }
}
