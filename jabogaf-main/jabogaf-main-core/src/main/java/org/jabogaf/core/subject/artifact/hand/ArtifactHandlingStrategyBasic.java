package org.jabogaf.core.subject.artifact.hand;

import org.jabogaf.api.artifact.Artifact;
import org.jabogaf.api.subject.SetterOfArtifactsForHands;
import org.jabogaf.api.subject.artifact.ArtifactHolder;
import org.jabogaf.api.subject.artifact.hand.*;
import org.jabogaf.api.subject.hand.Hand;

import javax.inject.Inject;

/**
 * The strategy that allows a {@link org.jabogaf.api.subject.GameSubject} to handle a concrete {@link
 * org.jabogaf.api.artifact.Artifact}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public abstract class ArtifactHandlingStrategyBasic implements ArtifactHandlingStrategy {

    @Inject
    private AddArtifactToHandStrategyGeneral addArtifactToHandStrategyGeneral;

    @Override
    public boolean canHandle(Artifact artifact, Hand.Type handType) {
        return getCanHandleArtifactStrategy().check(artifact, handType);
    }

    @Override
    public void addArtifactToHand(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) throws ArtifactHandlingException {
        addArtifactToHandStrategyGeneral.addArtifactToHand(createAddArtifactToHandStrategyContext(artifact, handType, artifactHolder, setterOfArtifactsForHands));
    }

    private AddArtifactToHandStrategyContext createAddArtifactToHandStrategyContext(Artifact artifact, Hand.Type handType, ArtifactHolder artifactHolder, SetterOfArtifactsForHands setterOfArtifactsForHands) {
        return new AddArtifactToHandStrategyContext().
                setArtifact(artifact).
                setHandType(handType).
                setArtifactHolder(artifactHolder).
                setSetterOfArtifactsForHands(setterOfArtifactsForHands).
                setAddArtifactToHandStrategyConcrete(getAddArtifactToHandStrategyConcrete()).
                setCanHandleArtifactStrategy(getCanHandleArtifactStrategy());
    }


    protected abstract AddArtifactToHandStrategyConcrete getAddArtifactToHandStrategyConcrete();

    protected abstract CanHandleArtifactStrategy getCanHandleArtifactStrategy();
}
