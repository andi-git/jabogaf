package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.artifact.NullArtifact;
import at.ahammer.boardgame.api.subject.artifact.hand.ArtifactHandlingStrategy;
import at.ahammer.boardgame.api.subject.SetterOfArtifactsForHands;
import at.ahammer.boardgame.core.board.field.FieldNull;
import at.ahammer.boardgame.core.subject.GameSubjectNull;

import java.util.List;

public class MyGameSubject extends GameSubjectNull {

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
