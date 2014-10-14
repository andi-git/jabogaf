package at.ahammer.boardgame.subject;

import at.ahammer.boardgame.artifact.Artifact;
import at.ahammer.boardgame.artifact.ArtifactException;
import at.ahammer.boardgame.artifact.HandCount;
import at.ahammer.boardgame.behavior.look.LookBehavior;
import at.ahammer.boardgame.behavior.look.LookNotPossibleException;
import at.ahammer.boardgame.behavior.move.FieldsNotConnectedException;
import at.ahammer.boardgame.behavior.move.MoveBehavior;
import at.ahammer.boardgame.behavior.move.MoveNotPossibleException;
import at.ahammer.boardgame.cdi.NewInstanceInGameContext;
import at.ahammer.boardgame.object.field.Field;
import at.ahammer.boardgame.subject.artifact.hand.ArtifactHandHelper;
import at.ahammer.boardgame.subject.artifact.hand.ArtifactHandStrategy;
import at.ahammer.heroquest.behavior.look.LookStrategies;
import at.ahammer.heroquest.behavior.move.MoveStrategies;

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

    private MoveBehavior moveBehavior;

    private LookBehavior lookBehavior;

    private Field position;

    public GameSubject(String id, Field position) {
        super(id);
        this.position = position;
        moveBehavior = fromGameContext(MoveStrategies.class).getMoveHero();
        lookBehavior = fromGameContext(LookStrategies.class).getLookHero();
    }

    public Artifact getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Artifact leftHand) {
        this.leftHand = leftHand;
        // FIXME create better change-strategy
        if (this.leftHand != null && this.leftHand.getHandCount() == HandCount.TWO) {
            this.rightHand = this.leftHand;
        }
    }

    public Artifact getRightHand() {
        return rightHand;
    }

    public void setRightHand(Artifact rightHand) {
        this.rightHand = rightHand;
        // FIXME create better change-strategy
        if (this.rightHand != null && this.rightHand.getHandCount() == HandCount.TWO) {
            this.leftHand = this.rightHand;
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

    public void move(Field to) throws FieldsNotConnectedException, MoveNotPossibleException {
        if (position != null) {
            if (!position.isConnected(to)) {
                throw new FieldsNotConnectedException("fields " + position + " and " + to + " are not connected");
            }
            if (moveBehavior.canMove(position, to)) {
                position = to;
            } else {
                throw new MoveNotPossibleException("unable to move from " + position + " to " + to);
            }
        } else {
            throw new IllegalStateException("position is null");
        }
    }

    public void look(Field target) throws LookNotPossibleException {
        if (!lookBehavior.canLook(position, target)) {
            throw new LookNotPossibleException("unable to look from " + position + " to " + target);
        }
    }

    public boolean canLook(Field target) {
        return lookBehavior.canLook(position, target);
    }

    public Field getPosition() {
        return position;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void setLookBehavior(LookBehavior lookBehavior) {
        this.lookBehavior = lookBehavior;
    }

}
