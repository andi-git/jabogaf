package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.behavior.look.LookBehavior;
import at.ahammer.boardgame.api.behavior.move.MoveBehavior;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.artifact.hand.SetterOfArtifactsForHands;
import at.ahammer.boardgame.core.board.field.FieldNull;

import java.util.List;

public class MyGameSubject extends GameSubject {

    public MyGameSubject() {
        super("mySubject", new FieldNull());
    }

    @Override
    public void clearArtifactHandlingStrategies() {
        super.clearArtifactHandlingStrategies();
    }

    @Override
    public List<ArtifactHandlingStrategy> getArtifactHandlingStrategies() {
        return super.getArtifactHandlingStrategies();
    }

    @Override
    public void addArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        super.addArtifactHandlingStrategy(artifactHandlingStrategy);
    }

    @Override
    public void removeArtifactHandlingStrategy(ArtifactHandlingStrategy artifactHandlingStrategy) {
        super.removeArtifactHandlingStrategy(artifactHandlingStrategy);
    }


    @Override
    public MoveBehavior getMoveBehavior() {
        return null;
    }

    @Override
    public LookBehavior getLookBehavior() {
        return null;
    }

    @Override
    public void changeMoveBehavior(MoveBehavior moveBehavior) { }

    @Override
    public void changeLookBehavior(LookBehavior lookBehavior) { }

    public SetterOfArtifactsForHands getSetterOfArtifactsForHands() {
        return (main, off) -> {
            getMainHand().setArtifact(main);
            getOffHand().setArtifact(off);
        };
    }

    public void resetHands() {
        getMainHand().setArtifact(new NullArtifact());
        getOffHand().setArtifact(new NullArtifact());
    }
}
