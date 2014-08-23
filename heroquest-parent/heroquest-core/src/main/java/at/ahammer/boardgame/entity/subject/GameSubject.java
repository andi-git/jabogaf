package at.ahammer.boardgame.entity.subject;

import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.entity.artifact.Artifact;
import at.ahammer.boardgame.entity.artifact.ArtifactException;
import at.ahammer.boardgame.entity.artifact.HandCount;
import at.ahammer.boardgame.entity.subject.artifact.hand.ArtifactHandHelper;
import at.ahammer.boardgame.entity.subject.artifact.hand.ArtifactHandStrategy;

import javax.enterprise.inject.spi.BeanManager;
import java.util.ArrayList;
import java.util.List;

/**
 * A subject (i.e. subject) in the game.
 * <p/>
 * Created by andreas on 26.07.14.
 */
public abstract class GameSubject extends NewInstanceInGameContext {

    private final List<ArtifactHandStrategy> handStrategies = new ArrayList<>();
    private Artifact leftHand;
    private Artifact rightHand;

    public GameSubject(String id) {
        super(id);
    }

    public Artifact getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Artifact leftHand) {
        this.leftHand = leftHand;
        // FIXME create better change-strategy
        if (this.leftHand != null && this.leftHand.getHandCount() == HandCount.TWO) {
            this.rightHand= this.leftHand;
        }
    }

    public Artifact getRightHand() {
        return rightHand;
    }

    public void setRightHand(Artifact rightHand) {
        this.rightHand = rightHand;
        // FIXME create better change-strategy
        if (this.rightHand != null && this.rightHand.getHandCount() == HandCount.TWO) {
            this.leftHand= this.rightHand;
        }
    }

    public void addArtifactForHand(Artifact artifact) throws ArtifactException {
        if (getArtifactHandHelper().canHandle(artifact, this)) {
            getArtifactHandHelper().addArtifact(artifact, this);
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

    private ArtifactHandHelper getArtifactHandHelper() {
        return fromGameContext(ArtifactHandHelper.class);
    }
}
