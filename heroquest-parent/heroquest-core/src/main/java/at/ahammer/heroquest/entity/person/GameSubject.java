package at.ahammer.heroquest.entity.person;

import at.ahammer.heroquest.entity.artifact.weapon.WeaponType;
import at.ahammer.heroquest.entity.artifact.Artifact;
import at.ahammer.heroquest.entity.artifact.ArtifactException;
import at.ahammer.heroquest.entity.artifact.weapon.Weapon;
import at.ahammer.heroquest.entity.person.artifact.hand.ArtifactHandHelper;
import at.ahammer.heroquest.entity.person.artifact.hand.ArtifactHandStrategy;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreas on 26.07.14.
 */
public abstract class GameSubject {

    private final List<ArtifactHandStrategy> handStrategies = new ArrayList<>();
    @Inject
    private ArtifactHandHelper artifactHandHelper;
    private String name;
    private Artifact leftHand;
    private Artifact rightHand;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Artifact getLeftHand() {
        if (leftHand == null && isOtherHandTwoHanded(rightHand)) {
            return rightHand;
        }
        return leftHand;
    }

    public void setLeftHand(Artifact leftHand) {
        this.leftHand = leftHand;
    }

    public Artifact getRightHand() {
        if (rightHand == null && isOtherHandTwoHanded(leftHand)) {
            return leftHand;
        }
        return rightHand;
    }

    public void setRightHand(Artifact rightHand) {
        this.rightHand = rightHand;
    }

    private boolean isOtherHandTwoHanded(Artifact otherHand) {
        return (otherHand instanceof Weapon) && ((Weapon) otherHand).getType() == WeaponType.TWO_HAND_ATTACK;
    }

    public void addArtifactForHand(Artifact artifact) throws ArtifactException {
        if (artifactHandHelper.canHandle(artifact, this)) {
            artifactHandHelper.addArtifact(artifact, this);
        } else {
            throw new ArtifactException("can not add " + artifact + " to " + toString());
        }
    }

    public List<ArtifactHandStrategy> getHandStrategies() {
        return handStrategies;
    }

    protected void addHandStrategy(ArtifactHandStrategy handStrategy) {
        this.handStrategies.add(handStrategy);
    }
}
