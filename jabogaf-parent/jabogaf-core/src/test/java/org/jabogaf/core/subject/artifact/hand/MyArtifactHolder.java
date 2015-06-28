package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.subject.SetterOfArtifactsForHands;
import org.jabogaf.core.subject.artifact.ArtifactHolderBasic;

import javax.enterprise.inject.Typed;

@Typed
public class MyArtifactHolder extends ArtifactHolderBasic {

    @Override
    public SetterOfArtifactsForHands createSetterOfArtifactsForHands() {
        return super.createSetterOfArtifactsForHands();
    }
}
