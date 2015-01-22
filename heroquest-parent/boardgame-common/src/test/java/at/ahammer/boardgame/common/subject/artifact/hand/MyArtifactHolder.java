package at.ahammer.boardgame.common.subject.artifact.hand;

import at.ahammer.boardgame.api.subject.SetterOfArtifactsForHands;
import at.ahammer.boardgame.core.subject.artifact.ArtifactHolderBasic;

import javax.enterprise.inject.Typed;

@Typed
public class MyArtifactHolder extends ArtifactHolderBasic {

    @Override
    public SetterOfArtifactsForHands createSetterOfArtifactsForHands() {
        return super.createSetterOfArtifactsForHands();
    }
}
