package at.ahammer.boardgame.api.subject.artifact.hand;

import at.ahammer.boardgame.api.artifact.Artifact;
import at.ahammer.boardgame.api.artifact.HandCount;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.hand.Hand;

import java.util.List;

/**
 * Helper class that contains all parameters for {@link at.ahammer.boardgame.api.subject.artifact.hand.AddArtifactToHandStrategyGeneral} and provides some "methods-shortcuts", i. e. it acts like a facade to methods in the assigned components.
 */
public class AddArtifactToHandStrategyContext {

    private Artifact artifact;
    private Hand.Type handType;
    private GameSubject gameSubject;
    private SetterOfArtifactsForHands setterOfArtifactsForHands;
    private CanHandleArtifactStrategy canHandleArtifactStrategy;
    private AddArtifactToHandStrategyConcrete addArtifactToHandStrategyConcrete;

    public AddArtifactToHandStrategyContext(Artifact artifact, Hand.Type handType, GameSubject gameSubject, SetterOfArtifactsForHands setterOfArtifactsForHands, CanHandleArtifactStrategy canHandleArtifactStrategy, AddArtifactToHandStrategyConcrete addArtifactToHandStrategyConcrete) {
        this.artifact = artifact;
        this.handType = handType;
        this.gameSubject = gameSubject;
        this.setterOfArtifactsForHands = setterOfArtifactsForHands;
        this.canHandleArtifactStrategy = canHandleArtifactStrategy;
        this.addArtifactToHandStrategyConcrete = addArtifactToHandStrategyConcrete;
    }

    public AddArtifactToHandStrategyContext() { }

    public Artifact getArtifact() {
        if (artifact == null) throw new IllegalStateException("artifact was not set");
        return artifact;
    }

    public AddArtifactToHandStrategyContext setArtifact(Artifact artifact) {
        this.artifact = artifact;
        return this;
    }

    public Hand.Type getHandType() {
        if (handType == null) throw new IllegalStateException("handType was not set");
        return handType;
    }

    public AddArtifactToHandStrategyContext setHandType(Hand.Type handType) {
        this.handType = handType;
        return this;
    }

    public GameSubject getGameSubject() {
        if (gameSubject == null) throw new IllegalStateException("gameSubject was not set");
        return gameSubject;
    }

    public AddArtifactToHandStrategyContext setGameSubject(GameSubject gameSubject) {
        this.gameSubject = gameSubject;
        return this;
    }

    public SetterOfArtifactsForHands getSetterOfArtifactsForHands() {
        if (setterOfArtifactsForHands == null) throw new IllegalStateException("setterOfArtifactsForHands was not set");
        return setterOfArtifactsForHands;
    }

    public AddArtifactToHandStrategyContext setSetterOfArtifactsForHands(SetterOfArtifactsForHands setterOfArtifactsForHands) {
        this.setterOfArtifactsForHands = setterOfArtifactsForHands;
        return this;
    }

    public CanHandleArtifactStrategy getCanHandleArtifactStrategy() {
        if (canHandleArtifactStrategy == null) throw new IllegalStateException("canHandleArtifactStrategy was not set");
        return canHandleArtifactStrategy;
    }

    public AddArtifactToHandStrategyContext setCanHandleArtifactStrategy(CanHandleArtifactStrategy canHandleArtifactStrategy) {
        this.canHandleArtifactStrategy = canHandleArtifactStrategy;
        return this;
    }

    public AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete() {
        if (addArtifactToHandStrategyConcrete == null) throw new IllegalStateException("addArtifactToHandStrategyConcrete was not set");
        return addArtifactToHandStrategyConcrete;
    }

    public AddArtifactToHandStrategyContext setAddArtifactToHandStrategyConcrete(AddArtifactToHandStrategyConcrete addArtifactToHandStrategyConcrete) {
        this.addArtifactToHandStrategyConcrete = addArtifactToHandStrategyConcrete;
        return this;
    }

    public boolean canHandleArtifact() {
        return getCanHandleArtifactStrategy().check(getArtifact(), getHandType());
    }

    public Artifact getMainHandArtifact() {
        return getGameSubject().getMainHandArtifact();
    }

    public HandCount getMainHandHandCount() {
        return getMainHandArtifact().getHandCount();
    }

    public Artifact getOffHandArtifact() {
        return getGameSubject().getOffHandArtifact();
    }

    public HandCount getOffHandHandCount() {
        return getOffHandArtifact().getHandCount();
    }

    public void addArtifactsToHands(Artifact mainHandArtifact, Artifact offHandArtifact) {
        getSetterOfArtifactsForHands().setHands(mainHandArtifact, offHandArtifact);
    }

    public void addArtifactToMainHand(Artifact mainHandArtifact) {
        getSetterOfArtifactsForHands().setHands(mainHandArtifact, getGameSubject().getOffHandArtifact());
    }

    public void addArtifactToMainHand() {
        addArtifactToMainHand(getArtifact());
    }

    public void addArtifactToOffHand(Artifact offHandArtifact) {
        getSetterOfArtifactsForHands().setHands(getGameSubject().getMainHandArtifact(), offHandArtifact);
    }

    public void addArtifactToOffHand() {
        addArtifactToOffHand(getArtifact());
    }

    public void addArtifactToBothHands(Artifact artifact) {
        getSetterOfArtifactsForHands().setHands(artifact, artifact);
    }

    public void addArtifactToBothHands() {
        addArtifactToBothHands(getArtifact());
    }

    public void resetHands() {
        getSetterOfArtifactsForHands().setHands(new NullArtifact(), new NullArtifact());
    }

    public void addArtifactToHand() {
        getAddArtifactToHandStrategyConcrete().addArtifactToHand(this);
    }

    public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
        return getGameSubject().getArtifactHandlingStrategies();
    }
}
